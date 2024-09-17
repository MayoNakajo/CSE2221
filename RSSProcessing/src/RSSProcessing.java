import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * This program inputs an XML RSS (version 2.0) feed from a given URL and
 * outputs various elements of the feed to the console.
 *
 * @author Put your name here
 *
 */
public final class RSSProcessing {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSProcessing() {
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of the {@code XMLTree} matching the
     *         given tag or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of the {@code XMLTree} matching the
     *   given tag or -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        int children = xml.numberOfChildren();
        int idx = -1;
        for (int i = 0; i < children; i++) {
            if (xml.child(i).label().equals(tag)) {
                idx = i;
            }
        }
        return idx;

    }

    /**
     * Processes one news item and outputs the title, or the description if the
     * title is not present, and the link (if available) with appropriate
     * labels.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures out.content = #out.content * [the title (or description) and
     *          link]
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        int childNum = item.numberOfChildren();
        int num;
        String[] tags = { "title", "link", "description", "pubDate", "source" };
        for (int j = 0; j < childNum; j++) {
            for (int i = 0; i < tags.length; i++) {
                num = getChildElement(item, tags[i]);
                if (num != -1) {
                    if (item.child(num).numberOfChildren() > 0) {
                        out.println(tags[i] + ": "
                                + item.child(num).child(0).label());
                    } else {
                        out.println(tags[i] + ": not available");
                    }
                }
            }
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open I/O streams.
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Input the source URL.
         */
        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();
        /*
         * Read XML input and initialize XMLTree. If input is not legal XML,
         * this statement will fail.
         */
        XMLTree xml = new XMLTree1(url);

        XMLTree channel = xml.child(0);

        int titleNum = getChildElement(channel, "title");
        if (titleNum != -1) {
            if (channel.child(titleNum).numberOfChildren() > 0) {
                out.println(
                        "Title: " + channel.child(titleNum).child(0).label());
            } else {
                out.println("Title : not available");
            }
        }

        int descripNum = getChildElement(channel, "description");
        if (descripNum != -1) {
            if (channel.child(descripNum).numberOfChildren() > 0) {
                out.println("Description: "
                        + channel.child(descripNum).child(0).label());
            } else {
                out.println("Description : not available");
            }
        }
        int linkNum = getChildElement(channel, "link");

        if (linkNum != -1) {
            if (channel.child(linkNum).numberOfChildren() > 0) {
                out.println("Link: " + channel.child(linkNum).child(0).label());
            } else {
                out.println("Link : not available");
            }
        }
        int numChild = channel.numberOfChildren();
        for (int i = 0; i < numChild; i++) {
            if (channel.child(i).label().equals("item")) {
                XMLTree item = channel.child(i);
                processItem(item, out);
            }
        }

        in.close();
        out.close();
    }

}
