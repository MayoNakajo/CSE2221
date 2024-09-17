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
public final class Hailstone3 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone3() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream and max value in series
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
        int max = arr[0];
        for (int k = 0; k < i; k++) {

            if (arr[k + 1] > max) {
                max = arr[k + 1];
            }
        }
        out.println("Max value in series: " + max);
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

        out.print("Enter 'y' to continue: ");
        String letter = in.nextLine();
        while (letter.equalsIgnoreCase("y")) {
            out.print("Enter a positive number: ");
            num = in.nextInteger();

            generateSeries(num, out);
            out.print("Enter 'y' to continue: ");
            letter = in.nextLine();
        }
        out.println("Program quit.");

        in.close();
        out.close();
    }

}
