package trans.am;

public class Utils {

    public static double limit(double n, double min, double max) {
        return Math.max(Math.min(max, n), min);
    }

}
