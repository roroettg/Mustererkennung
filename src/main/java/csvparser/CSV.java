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

    public Map<String, Sensor> getSensors() {
        return sensors;
    }

    /**
     *
     * @param args
     * ID,Timestamp,accelX (m/s^2),accelY (m/s^2),accelZ (m/s^2),gyroX (rad/s),gyroY (rad/s),gyroZ (rad/s),magnetX (uT),magnetY (uT),magnetZ (uT)
     */
    public static void main(String[] args) {
        File file;
        int delta = 10000;
        if(args.length <= 0) {
            file = new File("LernDaten/02/gehen.csv");
        } else {
            file = new File(args[0]);
        }
        if(args.length == 2) {
            delta = Integer.parseInt(args[1]);
        }

        CSV csv = new CSV(file);
        System.out.println("Initialisation finished\n\n");
        Map<String, Sensor> sensors = csv.sensors;
        JSONObject jsonObject = new JSONObject();


        int i = 0;
        for(Sensor sensor: sensors.values()) {
            JSONArray values = new JSONArray();

            JSONArray avgAccXD = new JSONArray();
            avgAccXD.addAll(StatisticHelper.averageWithDelta(sensor.getAccelX(), delta));

            JSONObject avgAccX = new JSONObject();
            avgAccX.put("avgAccXWithDelta", avgAccXD);
            JSONArray avgAccYD = new JSONArray();
            avgAccYD.addAll(StatisticHelper.averageWithDelta(sensor.getAccelY(), delta));
            JSONObject avgAccY = new JSONObject();
            avgAccY.put("avgAccYWithDelta", avgAccYD);
            JSONArray avgAccZD = new JSONArray();
            avgAccZD.addAll(StatisticHelper.averageWithDelta(sensor.getAccelZ(), delta));
            JSONObject avgAccZ = new JSONObject();
            avgAccZ.put("avgAccZWithDelta", avgAccZD);

            values.add(avgAccX);
            values.add(avgAccY);
            values.add(avgAccZ);

            System.out.println("Average calculated for Sensor" + sensor.getId());

            JSONObject stdAccX = new JSONObject();
            JSONArray stdAccXD = new JSONArray();
            stdAccXD.addAll(StatisticHelper.standardDeviationWithDelta(sensor.getAccelX(), delta));
            stdAccX.put("stdAccXWithDelta", stdAccXD);

            JSONObject stdAccY = new JSONObject();
            JSONArray stdAccYD = new JSONArray();
            stdAccYD.addAll(StatisticHelper.standardDeviationWithDelta(sensor.getAccelY(), delta));
            stdAccY.put("stdAccYWithDelta", stdAccYD);

            JSONObject stdAccZ = new JSONObject();
            JSONArray stdAccZD = new JSONArray();
            stdAccZD.addAll(StatisticHelper.standardDeviationWithDelta(sensor.getAccelZ(), delta));
            stdAccZ.put("stdAccZWithDelta", stdAccZD);

            values.add(stdAccX);
            values.add(stdAccY);
            values.add(stdAccZ);

            System.out.println("Standard Deviation calculated for Sensor" + sensor.getId());

            JSONObject medianAccX = new JSONObject();
            JSONArray medianAccDX = new JSONArray();
            medianAccDX.addAll(StatisticHelper.medianWithDelta(sensor.getAccelX(), delta));
            medianAccX.put("medianAccXWithDelta", medianAccDX);

            JSONObject medianAccY = new JSONObject();
            JSONArray medianAccDY = new JSONArray();
            medianAccDY.addAll(StatisticHelper.medianWithDelta(sensor.getAccelY(), delta));
            medianAccY.put("medianAccYWithDelta", medianAccDY);

            JSONObject medianAccZ = new JSONObject();
            JSONArray medianAccDZ = new JSONArray();
            medianAccDZ.addAll(StatisticHelper.medianWithDelta(sensor.getAccelZ(), delta));
            medianAccZ.put("medianAccZWithDelta", medianAccDZ);

            values.add(medianAccX);
            values.add(medianAccY);
            values.add(medianAccZ);
//
            System.out.println("Median calculated for Sensor" + sensor.getId());

            JSONObject rangeAccX = new JSONObject();
            JSONArray rangeAccDX = new JSONArray();
            rangeAccDX.addAll(StatisticHelper.rangeWithDelta(sensor.getAccelX(), delta));
            rangeAccX.put("rangeAccXWithDelta", rangeAccDX);

            JSONObject rangeAccY = new JSONObject();
            JSONArray rangeAccDY = new JSONArray();
            rangeAccDY.addAll(StatisticHelper.rangeWithDelta(sensor.getAccelY(), delta));
            rangeAccY.put("rangeAccYWithDelta", rangeAccDY);

            JSONObject rangeAccZ = new JSONObject();
            JSONArray rangeAccDZ = new JSONArray();
            rangeAccDZ.addAll(StatisticHelper.rangeWithDelta(sensor.getAccelZ(), delta));
            rangeAccZ.put("rangeAccZWithDelta", rangeAccDZ);

            values.add(rangeAccX);
            values.add(rangeAccY);
            values.add(rangeAccZ);

            System.out.println("Range calculated for Sensor" + sensor.getId());

            JSONObject maxAccX = new JSONObject();
            JSONArray maxAccDX = new JSONArray();
            maxAccDX.addAll(StatisticHelper.maxValueWithDelta(sensor.getAccelX(), delta));
            maxAccX.put("maxAccXWithDelta", maxAccDX);

            JSONObject maxAccY = new JSONObject();
            JSONArray maxAccDY = new JSONArray();
            maxAccDY.addAll(StatisticHelper.maxValueWithDelta(sensor.getAccelY(), delta));
            maxAccY.put("maxAccYWithDelta", maxAccDY);

            JSONObject maxAccZ = new JSONObject();
            JSONArray maxAccDZ = new JSONArray();
            maxAccDZ.addAll(StatisticHelper.maxValueWithDelta(sensor.getAccelZ(), delta));
            maxAccZ.put("maxAccZWithDelta", maxAccDZ);

            values.add(maxAccX);
            values.add(maxAccY);
            values.add(maxAccZ);

            System.out.println("MaxValue calculated for Sensor" + sensor.getId());

            JSONObject minAccX = new JSONObject();
            JSONArray minAccDX = new JSONArray();
            minAccDX.addAll(StatisticHelper.minValueWithDelta(sensor.getAccelX(), delta));
            minAccX.put("minAccXWithDelta", minAccDX);

            JSONObject minAccY = new JSONObject();
            JSONArray minAccDY = new JSONArray();
            minAccDY.addAll(StatisticHelper.minValueWithDelta(sensor.getAccelY(), delta));
            minAccY.put("minAccYWithDelta", minAccDY);

            JSONObject minAccZ = new JSONObject();
            JSONArray minAccDZ = new JSONArray();
            minAccDZ.addAll(StatisticHelper.minValueWithDelta(sensor.getAccelZ(), delta));
            minAccZ.put("minAccZWithDelta", minAccDZ);

            values.add(minAccX);
            values.add(minAccY);
            values.add(minAccZ);

            System.out.println("MinValue calculated for Sensor" + sensor.getId());


            JSONObject energyAccX = new JSONObject();
            JSONArray energyDX = new JSONArray();
            energyDX.addAll(StatisticHelper.energyWithDelta(sensor.getAccelX(), delta));
            energyAccX.put("a", energyDX);

            JSONObject energyAccY = new JSONObject();
            JSONArray energyAccDY = new JSONArray();
            energyAccDY.addAll(StatisticHelper.energyWithDelta(sensor.getAccelY(), delta));
            energyAccY.put("b", energyAccDY);

            JSONObject energyAccZ = new JSONObject();
            JSONArray energyAccDZ = new JSONArray();
            energyAccDZ.addAll(StatisticHelper.energyWithDelta(sensor.getAccelZ(), delta));
            energyAccZ.put("c", energyAccDZ);

            values.add(energyAccX);
            values.add(energyAccY);
            values.add(energyAccZ);

            System.out.println("Energy calculated for Sensor" + sensor.getId());

            JSONObject energyAbsAccX = new JSONObject();
            JSONArray energyAbsDAccX = new JSONArray();
            energyAbsDAccX.addAll(StatisticHelper.energyWithDeltaAbsolute(sensor.getAccelX(), delta));
            energyAbsAccX.put("energyAbsAccXWithDelta", energyAbsDAccX);

            JSONObject energyAbsAccY = new JSONObject();
            JSONArray energyAbsAccDY = new JSONArray();
            energyAbsAccDY.addAll(StatisticHelper.energyWithDeltaAbsolute(sensor.getAccelY(), delta));
            energyAbsAccY.put("energyAbsAccYWithDelta", energyAbsAccDY);

            JSONObject energyAbsAccZ = new JSONObject();
            JSONArray energyAbsAccDZ = new JSONArray();
            energyAbsAccDZ.addAll(StatisticHelper.energyWithDeltaAbsolute(sensor.getAccelZ(), delta));
            energyAbsAccZ.put("energyAbsAccZWithDelta", energyAbsAccDZ);

            values.add(energyAbsAccX);
            values.add(energyAbsAccY);
            values.add(energyAbsAccZ);

            System.out.println("energyAbs calculated for Sensor" + sensor.getId());

            JSONObject sensorValue = new JSONObject();
            sensorValue.put(sensor.getId(), values);
            jsonObject.put("delta", delta);
            jsonObject.put("sensor" + i++, sensorValue);


        }


        FileWriter fw = null;
        String newPath = file.toString().replace(".csv", ".json");
        try {
            fw = new FileWriter(newPath);
            fw.write(jsonObject.toJSONString());

//            jsonObject.writeJSONString(fw);
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + jsonObject.toJSONString());

            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();

        }








    }


}