import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This program asks the user for a password candidate and lets them know if it
 * satisfies certain criteria, and if not, which one(s) it fails. *
 *
 * @author Mayo Nakajo
 *
 */
public final class CheckPassword {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CheckPassword() {
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains an upper case letter, false otherwise
     */
    private static boolean containsUpperCaseLetter(String str) {
        boolean hasUpper = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isUpperCase(str.charAt(i))) {
                hasUpper = true;
            }
        }
        return hasUpper;
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains a lower case letter, false otherwise
     */
    private static boolean containsLowerCaseLetter(String str) {
        boolean hasLower = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLowerCase(str.charAt(i))) {
                hasLower = true;
            }
        }
        return hasLower;
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains a digit, false otherwise
     */
    private static boolean containsDigit(String str) {
        boolean hasDigit = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                hasDigit = true;
            }
        }
        return hasDigit;
    }

    /**
     * Checks if the given String contains an upper case letter.
     *
     * @param str
     *            the String to check
     * @return true if str contains a special char, false otherwise
     */
    private static boolean containsSpecial(String str) {
        boolean hasSpecial = false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isLetter(str.charAt(i))
                    && !Character.isDigit(str.charAt(i))) {
                hasSpecial = true;
            }
        }
        return hasSpecial;
    }

    /**
     * Checks whether the given STring satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param passwordCandidate
     *            the String to check
     * @param out
     *            the output stream
     * @param in
     *            the input stream
     */
    private static void checkPassword(String passwordCandidate,
            SimpleWriter out, SimpleReader in) {
        String password = passwordCandidate;
        //must be at least 8 characters long
        final int length = 8;
        while (password.length() < length) {
            out.print("Password must be at least 8 characters long. Enter a "
                    + "different password: ");
            password = in.nextLine();
        }
        //calls method to test each case
        boolean hasUpper = containsUpperCaseLetter(password);
        boolean hasLower = containsLowerCaseLetter(password);
        boolean hasDigit = containsDigit(password);
        boolean hasSpecial = containsSpecial(password);

        //keeps track of how many cases are true
        int counter = 0;
        if (hasUpper) {
            counter++;
        }
        if (hasLower) {
            counter++;
        }
        if (hasDigit) {
            counter++;
        }
        if (hasSpecial) {
            counter++;
        }

        while (counter < 3) {
            out.println("Password not approved.");
            if (!hasUpper) {
                out.println("Missing uppercase");
            }
            if (!hasLower) {
                out.println("Missing lowercase.");
            }
            if (!hasDigit) {
                out.println("Missing digit.");
            }
            if (!hasSpecial) {
                out.println("Missing special character.");
            }
            out.print("Enter different password: ");
            password = in.nextLine();
            checkPassword(password, out, in);
            //re-set the counter to 0 when trying a different password
            counter = 0;
        }
        if (counter >= 3) {
            out.print("Password approved.");
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

        out.print("Enter password: ");
        String password = in.nextLine();

        checkPassword(password, out, in);

        in.close();
        out.close();
    }

}
