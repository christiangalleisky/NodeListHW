package edu.gcccd.csis;

import org.junit.Assert.*;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class MyProject2Test {

    private static BigInteger genBigInteger(final NodeList<Integer> nodeList) {
        final StringBuilder sb = new StringBuilder();
        for (final int i : nodeList) {
            sb.append(i);
        }
        return new BigInteger(sb.toString());
    }

    // turns a String's digits into a NodeList, e.g. "100" => '1','0','0'
    private static NodeList genNodeList(final String s) {
        final NodeList nodeList = new NodeList<>();
        for (final char c : s.toCharArray()) {
            nodeList.append(Character.getNumericValue(c));
        }
        return nodeList;
    }

    /**
     * Visually inspect that the code is revered for this method. There will
     * be a print out of both the prior and after numbers.
     */
    @Test
    public void testReverseMethod(){
        final NodeList<Integer> n1 = Project2.generateNumber(7);
        final Iterator<Integer> itern1 = n1.iterator();
        final NodeList<Integer> n1Reversed = MyProject2.reverse(itern1);
        for(Integer x : n1Reversed){
            System.out.print(x);
        }
        System.out.println();
    }

    @Test
    public void testAdditionOfTwoNodeLists(){
        final NodeList<Integer> n1 = Project2.generateNumber(30);
        final NodeList<Integer> n2 = Project2.generateNumber(30);

        final BigInteger N1 = genBigInteger(n1);
        final BigInteger N2 = genBigInteger(n2);

        final NodeList<Integer> n3 = new MyProject2().addition(n1, n2);
        final BigInteger N3 = N1.add(N2);

        assertEquals(N3, genBigInteger(n3));

        final BigInteger N1_times_2 = N1.add(N1);
        final NodeList<Integer> nl_times_2 = new MyProject2().addition(n1, n1);

        assertEquals(N1_times_2, genBigInteger(nl_times_2));
    }

    @Test
    public void testAdditionOfListOfNodeLists(){
        NodeList<NodeList<Integer>> list = new NodeList<>(); // 72 nodelist all repre. 12345679
        for (int i = 0; i < 72; i++) {
            list.append(genNodeList("12345679"));
        }
        NodeList<Integer> result = new MyProject2().addition(list.iterator());
        assertEquals(new BigInteger("888888888"), genBigInteger(result));
    }

    @Test
    public void testLeadingZeros(){
        NodeList<Integer> n1 = genNodeList("007");
        NodeList<Integer> n2 = genNodeList("10");

        NodeList<Integer> n3 = new MyProject2().addition(n1, n2);
        assertEquals(new BigInteger("17"), genBigInteger(n3));

        // no leading 0s
        assertEquals(2, n3.getLength());
    }

    @Test
    public void testSave(){

    }

    @Test
    public void testLoad(){

    }
}
