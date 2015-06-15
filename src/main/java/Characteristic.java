import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

/**
 * Created by robin on 15.06.15.
 */
public class Characteristic {

    public static final int MAX_SENORS = 3;

    public HashMap<String, HashMap<String, Double>> characteristics = new HashMap<String, HashMap<String, Double>>();

    public Characteristic(JSONObject jsonObject) {
        String[] ids = new String[MAX_SENORS];
        for(int i = 0; i < MAX_SENORS; i++) {
            JSONObject dataObj = ((JSONObject)jsonObject.get("sensor" + i));
            String id = (String) ((JSONObject)jsonObject.get("sensor" + i)).keySet().toArray()[0];

            JSONArray array = (JSONArray) dataObj.get(id);

            HashMap<String, Double> values = new HashMap<String, Double>();
            for (int j = 0; j < array.size(); j++) {
                String valueTag = (String)((JSONObject) array.get(j)).keySet().toArray()[0];
                System.out.println(valueTag);
                Double value = (Double)((JSONObject) array.get(j)).values().toArray()[0];
                System.out.println(value);
                values.put(valueTag,value);
            }
            characteristics.put(id, values);

        }


    }
}
