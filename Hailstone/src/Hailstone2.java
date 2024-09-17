import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hailstone2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone2() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream and length of series
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int[] arr = new int[50];
        int i = 0;

        arr[0] = n;
        while (n != 1 && i < 50) {
            arr[i] = n;
            if (n % 2 == 0) {
                n = n / 2;
                i++;
            } else {
                n = (n * 3) + 1;
                i++;
            }
        }
        arr[i++] = 1;
        out.print("Hailstone series for " + n + ": ");
        for (int j = 0; j < i; j++) {
            out.print(arr[j] + " ");
        }
        out.println();
        out.println("Length of Hailstone series: " + i);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter a positive number: ");
        int num = in.nextInteger();

        generateSeries(num, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
