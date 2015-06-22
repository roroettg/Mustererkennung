package csvparser;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by robin on 01.06.15.
 */
public class StatisticHelper {

    /**
     * Berechnet das Minimum der Zahlenfolge
     * @return das Minimum
     */
    public static double minValue(Collection<Double> data)
    {
        double min = Double.MAX_VALUE;
        for(Double d : data) {
            if (d < min) min = d;
        }
        return min;
    }

    public static List<Double> minValueWithDelta(Map<Long, Double> data, int delta) {
        List<Double> processedData = new LinkedList<>();
        List<Double> subData = new LinkedList<Double>();
        Long[] timestamps = new Long[data.keySet().size()];
        data.keySet().toArray(timestamps);
        Double[] values = new Double[data.values().size()];
        data.values().toArray(values);


        for(int i = 0; i < timestamps.length-1; i++) {
            Long start = timestamps[i];
            Long nextTimestamp = timestamps[i];
            for(int j = 0; nextTimestamp - delta <= start && j < timestamps.length-1; j++, nextTimestamp = timestamps[j]) {
                subData.add(values[j]);
            }
            processedData.add(minValue(subData));
            subData.clear();
            Long nextStep = start;
            while (nextStep - start < 1000 && i < timestamps.length-1) {
                i++;
                nextStep = timestamps[i];
            }
        }
        return processedData;
    }


    /**
     * Berechnet das Maximum der Zahlenfolge
     * @return das Maximum
     */
    public static double maxValue(Collection<Double> data)
    {
        double max = -Double.MAX_VALUE;
        for(Double d : data) {
            if (d > max) max = d;
        }
        return max;
    }

    public static List<Double> maxValueWithDelta(Map<Long, Double> data, int delta) {
        List<Double> processedData = new LinkedList<>();
        List<Double> subData = new LinkedList<Double>();
        Long[] timestamps = new Long[data.keySet().size()];
        data.keySet().toArray(timestamps);
        Double[] values = new Double[data.values().size()];
        data.values().toArray(values);


        for(int i = 0; i < timestamps.length-1; i++) {
            Long start = timestamps[i];
            Long nextTimestamp = timestamps[i];
            for(int j = 0; nextTimestamp - delta <= start && j < timestamps.length-1; j++, nextTimestamp = timestamps[j]) {
                subData.add(values[j]);
            }
            processedData.add(maxValue(subData));
            subData.clear();
            Long nextStep = start;
            while (nextStep - start < 1000 && i < timestamps.length-1) {
                i++;
                nextStep = timestamps[i];
            }
        }
        return processedData;
    }

    public static double average(Collection<Double> data) {
        double result = 0;
        for(double d : data) {
            result +=d;
        }
        return result/data.size();
    }

    public static List<Double> averageWithDelta(Map<Long, Double> data, int delta) {
        List<Double> processedData = new LinkedList<>();
        List<Double> subData = new LinkedList<Double>();
        Long[] timestamps = new Long[data.keySet().size()];
        data.keySet().toArray(timestamps);
        Double[] values = new Double[data.values().size()];
        data.values().toArray(values);


        for(int i = 0; i < timestamps.length-1; i++) {
            Long start = timestamps[i];
            Long nextTimestamp = start;
            for(int j = 0; nextTimestamp - delta <= start && j < timestamps.length-1; j++, nextTimestamp = timestamps[j]) {
                subData.add(values[j]);
            }
            processedData.add(average(subData));
            subData.clear();
            Long nextStep = start;
            while (nextStep - start < 1000 && i < timestamps.length-1) {
                i++;
                nextStep = timestamps[i];
            }
        }
        return processedData;
    }

    public static double standardDeviation(Collection<Double> data) {
        double sdev = 0.0;
        double mean = average(data);
        for(Double d: data) {
            sdev += (d - mean) * (d - mean);
            sdev = Math.sqrt(sdev / (data.size() - 1.0));
        }
        return sdev;
    }

    public static List<Double> standardDeviationWithDelta(Map<Long, Double> data, int delta) {
        List<Double> processedData = new LinkedList<>();
        List<Double> subData = new LinkedList<Double>();
        Long[] timestamps = new Long[data.keySet().size()];
        data.keySet().toArray(timestamps);
        Double[] values = new Double[data.values().size()];
        data.values().toArray(values);


        for(int i = 0; i < timestamps.length-1; i++) {
            Long start = timestamps[i];
            Long nextTimestamp = timestamps[i];
            for(int j = 0; nextTimestamp - delta <= start && j < timestamps.length-1; j++, nextTimestamp = timestamps[j]) {
                subData.add(values[j]);
            }
            processedData.add(standardDeviation(subData));
            subData.clear();
            Long nextStep = start;
            while (nextStep - start < 1000 && i < timestamps.length-1) {
                i++;
                nextStep = timestamps[i];
            }

        }
        return processedData;
    }

    public static double median(Collection<Double> data) {
        List<Double> list = new LinkedList<Double>(data);
        Collections.sort(list);

        double[] a;

        double median = 0;
        double pos1 = Math.floor((list.size() - 1.0) / 2.0);
        double pos2 = Math.ceil((list.size() - 1.0) / 2.0);
        if (pos1 == pos2 ) {
            median = list.get((int)pos1);
        } else {
            median = (list.get((int)pos1) + list.get((int)pos2)) / 2.0 ;
        }

        return median;
    }
    public static List<Double> medianWithDelta(Map<Long, Double> data, int delta) {
        List<Double> processedData = new LinkedList<>();
        List<Double> subData = new LinkedList<Double>();
        Long[] timestamps = new Long[data.keySet().size()];
        data.keySet().toArray(timestamps);
        Double[] values = new Double[data.values().size()];
        data.values().toArray(values);


        for(int i = 0; i < timestamps.length-1; i++) {
            Long start = timestamps[i];
            Long nextTimestamp = timestamps[i];
            for(int j = 0; nextTimestamp - delta <= start && j < timestamps.length-1; j++, nextTimestamp = timestamps[j]) {
                subData.add(values[j]);
            }
            processedData.add(median(subData));
            subData.clear();
            Long nextStep = start;
            while (nextStep - start < 1000 && i < timestamps.length-1) {
                i++;
                nextStep = timestamps[i];
            }
        }
        return processedData;
    }

    public static void peakDetection(Collection<Double> data, double threshhold) {
        // peaks bestimmen über grenzwert
    }

    public static double range(Collection<Double> data) {
        return maxValue(data) - minValue(data);
    }

    public static List<Double> rangeWithDelta(Map<Long, Double> data, int delta) {
        List<Double> processedData = new LinkedList<>();
        List<Double> subData = new LinkedList<Double>();
        Long[] timestamps = new Long[data.keySet().size()];
        data.keySet().toArray(timestamps);
        Double[] values = new Double[data.values().size()];
        data.values().toArray(values);


        for(int i = 0; i < timestamps.length-1; i++) {
            Long start = timestamps[i];
            Long nextTimestamp = timestamps[i];
            for(int j = 0; nextTimestamp - delta <= start && j < timestamps.length-1; j++, nextTimestamp = timestamps[j]) {
                subData.add(values[j]);
            }
            processedData.add(range(subData));
            subData.clear();
            Long nextStep = start;
            while (nextStep - start < 1000 && i < timestamps.length-1) {
                i++;
                nextStep = timestamps[i];
            }
        }
        return processedData;
    }

    public static List<Double> energyWithDeltaAbsolute(Map<Long, Double> data, int delta) {
        List<Double> processedData = new LinkedList<>();
        List<Double> subData = new LinkedList<Double>();
        Long[] timestamps = new Long[data.keySet().size()];
        data.keySet().toArray(timestamps);
        Double[] values = new Double[data.values().size()];
        data.values().toArray(values);


        for(int i = 0; i < timestamps.length-1; i++) {
            Long start = timestamps[i];
            Long nextTimestamp = timestamps[i];
            for(int j = 0; nextTimestamp - delta <= start && j < timestamps.length-1; j++, nextTimestamp = timestamps[j]) {
                subData.add(values[j]);
            }
            Double factor = delta/(1.0 *subData.size());
            processedData.add(energyAbsolute(subData,factor));
            subData.clear();
            Long nextStep = start;
            while (nextStep - start < 1000 && i < timestamps.length-1) {
                i++;
                nextStep = timestamps[i];
            }
        }
        return processedData;

    }

    public static Double energyAbsolute(Collection<Double> data, Double factor) {
        Double value = 0.0;
        for(Double d : data) {
            value +=  Math.abs(d) * factor;
        }
        return value;
    }

    public static List<Double> energyWithDelta(Map<Long, Double> data, int delta) {
        List<Double> processedData = new LinkedList<>();
        List<Double> subData = new LinkedList<Double>();
        Long[] timestamps = new Long[data.keySet().size()];
        data.keySet().toArray(timestamps);
        Double[] values = new Double[data.values().size()];
        data.values().toArray(values);


        for(int i = 0; i < timestamps.length-1; i++) {
            Long start = timestamps[i];
            Long nextTimestamp = timestamps[i];
            for(int j = 0; nextTimestamp - delta <= start && j < timestamps.length-1; j++, nextTimestamp = timestamps[j]) {
                subData.add(values[j]);
            }
            Double factor = delta/(1.0 *subData.size());
            processedData.add(energy(subData, factor));
            subData.clear();
            Long nextStep = start;
            while (nextStep - start < 1000 && i < timestamps.length-1) {
                i++;
                nextStep = timestamps[i];
            }
        }
        return processedData;

    }

    public static Double energy(Collection<Double> data, Double factor) {
        Double value = 0.0;
        for(Double d : data) {
            value +=  d * factor;
        }
        return value;
    }
}
