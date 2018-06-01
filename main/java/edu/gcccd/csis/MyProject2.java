package edu.gcccd.csis;

import java.io.*;
import java.util.Iterator;

public class MyProject2 implements Project2 {

    /**
     * Returns a new NodeList that contains the elements of the source in reverse order.
     * The provided iterator's next method gets called n times, where n is the number of the items in the list.
     *
     * @param iter Iterator
     * @return NodeList in reverse order.
     */
    private static NodeList<Integer> reverse(final Iterator<Integer> iter) {
        if (iter.hasNext()) {
            final Integer i = iter.next();
            final NodeList<Integer> list = reverse(iter);
            list.append(i);
            return list;
        } else {
            return new NodeList<>();
        }
    }

    /**
     * Returns a new NodeList that represents the sum of the provided NodeLists.
     * The provided iterator's next method gets called (3m + 2n + 1) times,
     * where m is the length of the larger list and n the length of the smaller list
     *
     * @param nodeList1 NodeList&lt;Integer&gt;
     * @param nodeList2 NodeList&lt;Integer&gt;
     * @return NodeList, sum of the input.
     */
    @Override
    public NodeList<Integer> addition(final NodeList<Integer> nodeList1, final NodeList<Integer> nodeList2) {
        final Iterator<Integer> a = reverse(nodeList1.iterator()).iterator();
        final Iterator<Integer> b = reverse(nodeList2.iterator()).iterator();
        NodeList<Integer> c = new NodeList<>();
        int z = 0;
        while (a.hasNext() || b.hasNext()) {
            int x = a.hasNext() ? a.next() : 0;
            int y = b.hasNext() ? b.next() : 0;
            c.append((x + y + z) % 10);
            z = (x + y + z) / 10;
        }
        if (z != 0) {
            c.append(z);
        }
        c = reverse(c.iterator());         // reverse back to default representation
        while (c.getLength() > 1 && c.iterator().hasNext() && c.iterator().next().equals(0)) { // remove leading 0s .. .
            c.remove(0);
        }
        return (c);
    }

    /**
     * Returns a new NodeList that represents the sum of the provided NodeLists.
     * Simplified estimate for adding two lists: (3m + 2n + 1) = 5*L/2 + 1
     * Therefore (L-1)(5*L/2 + 1) = L squared for Big O
     *
     * @param iterator NodeList&lt;Integer&gt;
     * @return NodeList, sum of the input.
     */
    @Override
    public NodeList<Integer> addition(final Iterator<NodeList<Integer>> iterator) {
        NodeList<Integer> n0 = iterator.hasNext() ? iterator.next() : new NodeList<>();
        while (iterator.hasNext()) {
            n0 = addition(n0, iterator.next());  // addition is called L-1 times.
        }
        return n0;
    }

    @Override
    public void save(final NodeList<Integer> nodeList, final String fileName) {
        try (final OutputStream os = new BufferedOutputStream(new FileOutputStream(fileName))) {
            for (final Integer i : nodeList) {
                os.write(i);
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    @Override
    public NodeList<Integer> load(final String fileName) {
        final NodeList<Integer> nodeList = new NodeList<>();
        try (final InputStream is = new BufferedInputStream(new FileInputStream(fileName))) {
            while (0 < is.available()) {
                nodeList.append(is.read());
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        return nodeList;
    }
}