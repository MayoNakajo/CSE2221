import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Asks the user to enter their change amount and outputs the numbers of coins
 * of each kind.
 *
 * @author Put your name here
 *
 */
public final class CoinChange1 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange1() {
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

        out.print("Enter change amount in cents: ");
        int change = in.nextInteger();
        int dollar = 0;
        int halfD = 0;
        int quarter = 0;
        int dime = 0;
        int nickel = 0;
        int penny = 0;

        dollar = change / 100;
        change %= 100;
        halfD = change / 50;
        change %= 50;
        quarter = change / 25;
        change %= 25;
        dime = change / 10;
        change %= 10;
        nickel = change / 5;
        change %= 5;
        penny = change;
        out.print("You have " + dollar + " dollars, " + halfD + " half dollars,"
                + " " + quarter + " quarters, " + dime + " dimes, " + nickel
                + " nickels, " + penny + " pennies.");

        in.close();
        out.close();
    }

}
