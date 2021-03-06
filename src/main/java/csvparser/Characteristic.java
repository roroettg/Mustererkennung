package csvparser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

/**
 * Created by robin on 15.06.15.
 */
public class Characteristic {

    private final int sensorCount;
    // Map<id, Map<valueTag, value>
    public Map<String, LinkedHashMap<String, Double[]>> characteristics = new LinkedHashMap<String, LinkedHashMap<String, Double[]>>();

    public Characteristic(JSONObject jsonObject, int sensorCount) {
        this.sensorCount = sensorCount;
        String[] ids = new String[sensorCount];
        for(int i = 0; i < sensorCount; i++) {
            JSONObject dataObj = ((JSONObject)jsonObject.get("sensor" + i));
            String id = (String) ((JSONObject)jsonObject.get("sensor" + i)).keySet().toArray()[0];

            JSONArray array = (JSONArray) dataObj.get(id);

            LinkedHashMap<String, Double[]> valueMap = new LinkedHashMap<>();

            for (int j = 0; j < array.size(); j++) {
                String valueTag = (String) ((JSONObject) array.get(j)).keySet().toArray()[0];
                JSONArray valueArray = (JSONArray)((JSONObject) array.get(j)).values().toArray()[0];
                Double[] values = new Double[valueArray.size()];
                for(int k = 0; k < valueArray.size(); k++) {
                    values[k] = (Double)valueArray.get(k);
                }
                valueMap.put(valueTag, values);
            }
            characteristics.put(id, valueMap);
        }

    }


}
