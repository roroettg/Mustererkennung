import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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


    /**
     * Berechnet das Maximum der Zahlenfolge
     * @return das Maximum
     */
    public static double maxValue(Collection<Double> data)
    {
        double max = Double.MIN_VALUE;
        for(Double d : data) {
            if (d > max) max = d;
        }
        return max;
    }

    public static double average(Collection<Double> data) {
        double result = 0;
        for(double d : data) {
            result +=d;
        }
        return result/data.size();
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

    public static void peakDetection(Collection<Double> data, double threshhold) {
        // peaks bestimmen über grenzwert
    }

    public static double range(Collection<Double> data) {
        return maxValue(data) - minValue(data);
    }
}
