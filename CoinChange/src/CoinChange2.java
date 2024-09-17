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
public final class CoinChange2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoinChange2() {
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

        int[] denom = { 100, 50, 25, 10, 5, 1 };

        String[] coins = { "dollar", "halfD", "quarter", "dime", "nickel",
                "penny" };
        int[] amt = new int[6];
        for (int i = 0; i < denom.length; i++) {
            amt[i] = change / denom[i];
            out.println(coins[i] + ": " + amt[i] + " ");
            change %= denom[i];
        }

        in.close();
        out.close();
    }

}
