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
public final class Hailstone5 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone5() {
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
        out.println("max value in series: " + max);
    }

    /**
     * Checks whether the given {@code String} represents a valid integer value
     * in the range Integer.MIN_VALUE..Integer.MAX_VALUE.
     *
     * @param s
     *            the {@code String} to be checked
     * @return true if the given {@code String} represents a valid integer,
     *         false otherwise
     * @ensures canParseInt = [the given String represents a valid integer]
     */
    public static boolean canParseInt(String s) {
        assert s != null : "Violation of: s is not null";
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        int posNum = 0;
        out.print("Enter positive number: ");
        String num = in.nextLine();
        if (canParseInt(num) == true) {
            posNum = Integer.parseInt(num);
            if (posNum > 0) {
                generateSeries(posNum, out);
            }
        }
        while (canParseInt(num) != true) {
            getPositiveInteger(in, out);
        }

        return posNum;
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

        int posNum = getPositiveInteger(in, out);

        //generateSeries(num, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
