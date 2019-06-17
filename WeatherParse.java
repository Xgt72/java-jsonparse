import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Compile : javac -cp .:json-simple-1.1.1.jar WeatherParse.java
// Execute : java -cp .:json-simple-1.1.1.jar WeatherParse

public class WeatherParse {

    private static String JSON_WEATHER_PATH = "weather.json";

    public static void main(String[] args) {

        FileReader jsonFile = null;
        try {
            // lecture du fichier json
            jsonFile = new FileReader(JSON_WEATHER_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        // TODO parser le fichier
        JSONParser parser = new JSONParser();
        Object jsonParsed = null;
        try {
            jsonParsed = parser.parse(jsonFile);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        // TODO récupérer la racine du document
        JSONObject root = (JSONObject) jsonParsed;

        // TODO afficher la valeur de l'attribut "name" de la racine
        String name = (String) root.get("name");
        System.out.println("City name: " + name);

        // TODO afficher les valeurs des attributs "lat" et "lon" de l'élément "coord" contenu dans la racine
        JSONObject coordinates = (JSONObject) root.get("coord");
        double lat = (double) coordinates.get("lat");
        double lon = (double) coordinates.get("lon");
        System.out.println("City latitude: " + Double.toString(lat));
        System.out.println("City longitude: " + Double.toString(lon));
        // TODO parcourir tous les éléments de "weather" et afficher le contenu de "main"
        JSONArray weather = (JSONArray) root.get("weather");
        for (int i = 0; i < weather.size(); i++) {
            JSONObject dayWeather = (JSONObject) weather.get(i);
            String mainWeather = (String) dayWeather.get("main");
            System.out.println("Weather: " + mainWeather);
        }

        /*
            Résultat attendu :
            * City name: London
            * City latitude: 51.51
            * City longitude: -0.13
            * Weather: Drizzle
            * Weather: Clear
        */
    }
}
