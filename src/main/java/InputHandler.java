

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.SplittableRandom;

/**
 * Created by robin on 15.06.15.
 */
public class InputHandler {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("/home/robin/Mustererkennung/Measurements/LernDaten/02/gehen.json"));

            Characteristic c = new Characteristic(jsonObject);
            for(String key: c.characteristics.keySet()) {
                HashMap<String, Double>  data = c.characteristics.get(key);
                System.out.println("Sensor: " + key);
                for(String datakey : data.keySet()) {
                    System.out.println("Datakey: " + datakey + "Value: " + data.get(datakey));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}
