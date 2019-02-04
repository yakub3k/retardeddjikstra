package djikstra;

public final class Utils {

    public static final boolean DEBUG = true;

    private Utils() {
    }

    public static int[][] createSquarArray(int n, int defaultValue) {
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = defaultValue;
            }
        }
        return result;
    }

    public static int[] createLinearArray(int n, int defaultValue) {
        int[] result = new int[n];
            for (int j = 0; j < n; j++) {
                result[j] = defaultValue;
            }
        return result;
    }

    public static void log(String log){
        if (DEBUG)
            System.out.println(log);
    }
}
