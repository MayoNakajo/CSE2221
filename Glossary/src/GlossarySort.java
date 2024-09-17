import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Layered implementations of secondary method {@code sort} for
 * {@code Queue<String>}.
 */
public final class GlossarySort extends Queue1L<String> {

    /**
     * No-argument constructor.
     */
    public GlossarySort() {
        super();
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * perms(q * <removeMin>, #q)  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        assert q != null : "Violation of: q is not null";
        assert order != null : "Violation of: order is not null";

        Queue<String> temp = q.newInstance();
        String min = q.front();
        // iterate through all elements in q
        for (String s : q) {

            // checks if s is smaller than current min
            if (order.compare(s, min) < 0) {

                min = s;
            }
        }
        // if s is not min, add to temp
        for (String s : q) {
            if (!s.equals(min)) {
                temp.enqueue(s);
            }
        }
        q.transferFrom(temp);
        return min;

    }

    @Override
    public void sort(Comparator<String> order) {
        assert order != null : "Violation of: order is not null";

        Queue<String> temp = this.newInstance();
        temp.transferFrom(this);

        while (temp.length() > 0) {
            String min = removeMin(temp, order);
            this.enqueue(min);
        }

    }

}
