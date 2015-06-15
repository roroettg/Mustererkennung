package csvparser;

import com.opencsv.CSVReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Robin on 18.05.2015.
 */
public class CSV {

    private long tStart=0;
    private long tEnd=0;
    private Map<String, Sensor> sensors = new LinkedHashMap<String, Sensor>();

    public CSV(File file) {
        try {
        	CSVReader csvReader = new CSVReader(new FileReader(file));
            CSVReader iniReader = new CSVReader(new FileReader(new File(file.toString()+".ini")));



            // Reading INI File for config
            String [] iniNextLine;
            iniReader.readNext();
            while ((iniNextLine = iniReader.readNext()) != null) {
                if(iniNextLine[0].contains("movementStartTimestamp")) {
                    if(iniNextLine[0].split("=").length > 1) {
                        tStart = Long.parseLong(iniNextLine[0].split("=")[1]);
                    }

                }
                if(iniNextLine[0].contains("movementEndTimestamp")) {
                    if(iniNextLine[0].split("=").length > 1) {
                        tEnd = Long.parseLong(iniNextLine[0].split("=")[1]);
                    }
                }
            }

            // Reading CSV File
            String [] csvNextLine;
            csvReader.readNext();
            while ((csvNextLine = csvReader.readNext()) != null) {
                String id = csvNextLine[0];
                if(!sensors.containsKey(id)) {
                    sensors.put(id, new Sensor(id));
                }

                Sensor sensor = sensors.get(id);
                long timestamp = Long.parseLong(csvNextLine[1]);
                if(timestamp < tStart || timestamp > tEnd
                        && tStart != 0 && tEnd != 0) {
                    continue;
                }
                double accX = Double.parseDouble(csvNextLine[2]);
                double accY = Double.parseDouble(csvNextLine[3]);
                double accZ = Double.parseDouble(csvNextLine[4]);

                sensor.getAccelX().put(timestamp, accX);
                sensor.getAccelY().put(timestamp, accY);
                sensor.getAccelZ().put(timestamp, accZ);



            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sensors.size() + " sensors initialized");
        for(Sensor sensor : sensors.values()) {
            System.out.println(sensor.getAccelX().values().size() + " datapoints for AccX for sensor " + sensor.getId());
            System.out.println(sensor.getAccelY().values().size() + " datapoints for AccY for sensor " + sensor.getId());
            System.out.println(sensor.getAccelZ().values().size() + " datapoints for Accz for sensor " + sensor.getId());
        }
    }




    /**
     *
     * @param args
     * ID,Timestamp,accelX (m/s^2),accelY (m/s^2),accelZ (m/s^2),gyroX (rad/s),gyroY (rad/s),gyroZ (rad/s),magnetX (uT),magnetY (uT),magnetZ (uT)
     */
    public static void main(String[] args) {
        File file;
        if(args.length <= 0) {
            file = new File("/home/robin/Mustererkennung/Measurements/Ruhelage/e1.csv");
        } else {
            file = new File(args[0]);
        }

        CSV csv = new CSV(file);
        System.out.println("Initialisation finished\n\n");
        Map<String, Sensor> sensors = csv.sensors;
        JSONObject jsonObject = new JSONObject();

        int i = 0;
        for(Sensor sensor: sensors.values()) {
            JSONArray values = new JSONArray();

            JSONObject avgAccX = new JSONObject();
            avgAccX.put("averageAccX",StatisticHelper.average(sensor.getAccelX().values()));
            JSONObject avgAccY = new JSONObject();
            avgAccY.put("averageAccY",StatisticHelper.average(sensor.getAccelY().values()));
            JSONObject avgAccZ = new JSONObject();
            avgAccZ.put("averageAccZ",StatisticHelper.average(sensor.getAccelZ().values()));

            values.add(avgAccX);
            values.add(avgAccY);
            values.add(avgAccZ);

            System.out.println("Average calculated for Sensor" + sensor.getId());

            JSONObject stdAccX = new JSONObject();
            stdAccX.put("stdAccX", StatisticHelper.standardDeviation(sensor.getAccelX().values()));
            JSONObject stdAccY = new JSONObject();
            stdAccY.put("stdAccY", StatisticHelper.standardDeviation(sensor.getAccelY().values()));
            JSONObject stdAccZ = new JSONObject();
            stdAccZ.put("stdAccZ", StatisticHelper.standardDeviation(sensor.getAccelZ().values()));

            values.add(stdAccX);
            values.add(stdAccY);
            values.add(stdAccZ);

            System.out.println("Standard Deviation calculated for Sensor" + sensor.getId());

            JSONObject medianAccX = new JSONObject();
            medianAccX.put("medianAccX", StatisticHelper.median(sensor.getAccelX().values()));
            JSONObject medianAccY = new JSONObject();
            medianAccY.put("medianAccY", StatisticHelper.median(sensor.getAccelY().values()));
            JSONObject medianAccZ = new JSONObject();
            medianAccZ.put("medianAccZ", StatisticHelper.median(sensor.getAccelZ().values()));

            values.add(medianAccX);
            values.add(medianAccY);
            values.add(medianAccZ);

            System.out.println("Median calculated for Sensor" + sensor.getId());

            JSONObject rangeAccX = new JSONObject();
            rangeAccX.put("rangeAccX", StatisticHelper.range(sensor.getAccelX().values()));
            JSONObject rangeAccY = new JSONObject();
            rangeAccY.put("rangeAccY", StatisticHelper.range(sensor.getAccelY().values()));
            JSONObject rangeAccZ = new JSONObject();
            rangeAccZ.put("rangeAccZ", StatisticHelper.range(sensor.getAccelZ().values()));

            values.add(rangeAccX);
            values.add(rangeAccY);
            values.add(rangeAccZ);

            System.out.println("Range calculated for Sensor" + sensor.getId());

            JSONObject maxAccX = new JSONObject();
            maxAccX.put("maxAccX", StatisticHelper.maxValue(sensor.getAccelX().values()));
            JSONObject maxAccY = new JSONObject();
            maxAccY.put("maxAccY", StatisticHelper.maxValue(sensor.getAccelY().values()));
            JSONObject maxAccZ = new JSONObject();
            maxAccZ.put("maxAccZ", StatisticHelper.maxValue(sensor.getAccelZ().values()));

            values.add(maxAccX);
            values.add(maxAccY);
            values.add(maxAccZ);

            System.out.println("MaxValue calculated for Sensor" + sensor.getId());

            JSONObject minAccX = new JSONObject();
            minAccX.put("minAccX", StatisticHelper.minValue(sensor.getAccelX().values()));
            JSONObject minAccY = new JSONObject();
            minAccY.put("minAccY", StatisticHelper.minValue(sensor.getAccelY().values()));
            JSONObject minAccZ = new JSONObject();
            minAccZ.put("minAccZ", StatisticHelper.minValue(sensor.getAccelZ().values()));

            values.add(minAccX);
            values.add(minAccY);
            values.add(minAccZ);

            System.out.println("MaxValue calculated for Sensor" + sensor.getId());

            JSONObject sensorValue = new JSONObject();
            sensorValue.put(sensor.getId(), values);
            jsonObject.put("sensor" + i++, sensorValue);

        }


        FileWriter fw = null;
        String newPath = file.toString().replace(".csv", ".json");
        try {
            fw = new FileWriter(newPath);
            fw.write(jsonObject.toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + jsonObject.toString());

            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();

        }








    }
}