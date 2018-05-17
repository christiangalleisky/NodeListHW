package edu.gcccd.csis;

import org.junit.Assert.*;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.function.Predicate;

public class MyProject2Test {

    /**
     * Visually inspect that the code is revered for this method. There will
     * be a print out of both the prior and after numbers.
     */
    @Test
    public void testReverseMethod(){
        final NodeList<Integer> n1 = Project2.generateNumber(10);
        final Iterator<Integer> itern1 = n1.iterator();
        final NodeList<Integer> n1Reversed = MyProject2.reverse(itern1);
        for(Integer x : n1Reversed){
            System.out.print(x);
        }
        System.out.println();
    }

    @Test
    public void testAdditionOfTwoNodeLists(){
        final NodeList<Integer> n1 = Project2.generateNumber(7);
        final NodeList<Integer> n2 = Project2.generateNumber(7);
        final Project2 p = new MyProject2();
        final NodeList<Integer> n3 = p.addition(n1, n2);
        Project2.print(n3);
    }

    @Test
    public void testAdditionOfListOfNodeLists(){
        final NodeList<NodeList<Integer>> listOfLists = new NodeList<>();
        for (int i = 0; i < 5; i++) {
            listOfLists.append(Project2.generateNumber(7));
        }
        final Project2 p = new MyProject2();
        NodeList<Integer> n1 = p.addition(listOfLists.iterator());
        System.out.println("The final added value is:");
        Project2.print(n1);
    }

    @Test
    public void testSave(){

    }

    @Test
    public void testLoad(){

    }
}
