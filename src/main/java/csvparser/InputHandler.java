package csvparser;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by robin on 15.06.15.
 */
public class InputHandler {

    public static void main(String[] args) {
        int sensorCount = 6;
        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("Ruhelage/e1.json"));

            Characteristic c = new Characteristic(jsonObject, sensorCount);
            for(String key: c.characteristics.keySet()) {
                LinkedHashMap<String, Double[]> data = c.characteristics.get(key);
                System.out.println("Sensor: " + key);
                for(String datakey : data.keySet()) {
                    System.out.printf(datakey);
                    Double[] values = data.get(datakey);
                    for(Double d: values) {
                        System.out.println("Value: " + d);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}
