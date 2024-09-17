import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
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
public final class Hailstone1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Hailstone1() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * {@code NaturalNumber}.
     *
     * @param n
     *            the starting natural number
     * @param out
     *            the output stream
     * @updates out.content
     * @requires n > 0 and out.is_open
     * @ensures out.content = #out.content * [the Hailstone series starting with
     *          n]
     */
    private static void generateSeries(NaturalNumber n, SimpleWriter out) {

        NaturalNumber zero = new NaturalNumber2(0);
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);
        NaturalNumber three = new NaturalNumber2(3);

        out.print(n + " ");
        while (n.compareTo(one) > 0) {
            NaturalNumber rem = n.divide(two);
            // restore n
            n.multiply(two);
            n.add(rem);

            if (rem.isZero()) {
                n.divide(two);
            } else {
                n.multiply(three);
                n.increment();
            }
            generateSeries(n, out);
        }

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

        out.print("Enter a positive integer: ");
        String i = in.nextLine();
        while (!i.isEmpty()) {
            NaturalNumber n = new NaturalNumber2(i);
            generateSeries(n, out);
            out.println();
            out.print("Enter a positive integer: ");
            i = in.nextLine();
        }
        in.close();
        out.close();
    }

}
