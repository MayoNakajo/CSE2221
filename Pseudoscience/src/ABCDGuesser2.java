import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * This program uses the numbers id Jagers array to compute user's value with 1%
 *
 * @author Mayo Nakajo
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     *
     * @param wA
     *            w to a power
     * @param xB
     *            x to a power
     * @param yC
     *            y to a power
     * @param zD
     *            z to a power
     * @return multiplied value of four doubles
     */
    private static double calculate(double wA, double xB, double yC,
            double zD) {
        double num = wA * xB * yC * zD;
        return num;
    }

    /**
     * Computes a number close to user entered value using w, x, y, z with
     * Jager.
     *
     * @param u
     *            the constant to be approximated
     * @param w
     *            number to calculate approximation
     * @param x
     *            number to calculate approximation
     * @param y
     *            number to calculate approximation
     * @param z
     *            number to calculate approximation
     * @return approximate number close to user entered value within 1%
     */

    private static double jager(double u, double w, double x, double y,
            double z) {
        // array of numbers in Jager's formula
        final double[] arr = { -5.0, -4.0, -3.0, -2.0, -1.0, -1.0 / 2.0,
                -1.0 / 3.0, -1.0 / 4.0, 0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1.0,
                2.0, 3.0, 4.0, 5.0 };
        // approximated value must fall within this percent
        double newNum = 0;
        double bestNum = 0;

        // iterate through the entire array
        for (int i = 0; i < arr.length; i++) {
            double wA = Math.pow(w, arr[i]);
            for (int j = 0; j < arr.length; j++) {
                double xB = Math.pow(x, arr[j]);
                for (int k = 0; k < arr.length; k++) {
                    double yC = Math.pow(y, arr[k]);
                    for (int l = 0; l < arr.length; l++) {
                        double zD = Math.pow(z, arr[l]);
                        // new method calculate to multiply the 4 numbers
                        newNum = calculate(wA, xB, yC, zD);
                        if (Math.abs(newNum - u) < Math.abs(bestNum - u)) {
                            bestNum = newNum;
                        }
                    }
                }
            }
        }
        return bestNum;
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        double num;
        out.print("Enter a positive real number: ");
        String str = in.nextLine();
        // if the string can be converted to Double
        while (!FormatChecker.canParseDouble(str)) {
            out.print("Must enter a positive real number. Enter a number: ");
            str = in.nextLine();
        }
        num = Double.parseDouble(str);
        return num;
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        double num = getPositiveDouble(in, out);
        while (num == 1.0) {
            out.print("Cannot enter 1.0. Enter different number: ");
            num = in.nextDouble();
        }
        return num;
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
        // user entered value of u
        double u = getPositiveDouble(in, out);
        // user entered values to approximate u using Jager's formula
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        double num = jager(u, w, x, y, z);
        out.println("Approximated value: " + num);
        out.println("Actual value: " + u);
        in.close();
        out.close();
    }

}
