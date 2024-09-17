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
public final class IntegerRoot {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private IntegerRoot() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void myMethod(int n, int r, int guess, SimpleWriter out,
            SimpleReader in) {
        int lowEnough = 0;
        int tooHigh = n + 1;
        int sub = tooHigh - lowEnough;
        double dR = r;
        double dN = n;
        double root = Math.pow(dN, (1.0 / dR));
        out.println("n: " + n + " root: " + root);
        while (sub != 1) {
            if (root > guess) {
                tooHigh = guess;
                sub = tooHigh - lowEnough;
                out.print("Guess too low, try again: ");
                guess = in.nextInteger();
            } else if (root < guess) {
                lowEnough = guess;
                sub = tooHigh - lowEnough;
                out.println("Guess too high, try again: ");
                guess = in.nextInteger();
            } else {
                lowEnough = guess;
                sub = tooHigh - lowEnough;
                out.print("Guess is correct.");
            }
        }
        out.print("Correct answer is " + lowEnough);
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

        out.print("Enter a number: ");
        int n = in.nextInteger();
        out.print("Enter a power value: ");
        int r = in.nextInteger();
        out.print("Enter root guess: ");
        int guess = in.nextInteger();
        myMethod(n, r, guess, out, in);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
