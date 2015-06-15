package csvparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Robin on 18.05.2015.
 */
public class Sensor {

    private String id;
    private int size;
    private Map<Long, Double> accelX = new HashMap<Long, Double>();
    private Map<Long, Double> accelY = new HashMap<Long, Double>();
    private Map<Long, Double> accelZ = new HashMap<Long, Double>();;

    private Map<Long, Double> gyroX = new HashMap<Long, Double>();
    private Map<Long, Double> gyroY = new HashMap<Long, Double>();
    private Map<Long, Double> gyroZ = new HashMap<Long, Double>();;


    public Sensor(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<Long, Double> getAccelX() {
        return accelX;
    }

    public void setAccelX(Map<Long, Double> accelX) {
        this.accelX = accelX;
    }

    public Map<Long, Double> getAccelY() {
        return accelY;
    }

    public void setAccelY(Map<Long, Double> accelY) {
        this.accelY = accelY;
    }

    public Map<Long, Double> getAccelZ() {
        return accelZ;
    }

    public void setAccelZ(Map<Long, Double> accelZ) {
        this.accelZ = accelZ;
    }

    public Map<Long, Double> getGyroX() {
        return gyroX;
    }

    public void setGyroX(Map<Long, Double> gyroX) {
        this.gyroX = gyroX;
    }

    public Map<Long, Double> getGyroY() {
        return gyroY;
    }

    public void setGyroY(Map<Long, Double> gyroY) {
        this.gyroY = gyroY;
    }

    public Map<Long, Double> getGyroZ() {
        return gyroZ;
    }

    public void setGyroZ(Map<Long, Double> gyroZ) {
        this.gyroZ = gyroZ;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sensor)) return false;

        Sensor sensor = (Sensor) o;

        if (!id.equals(sensor.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
