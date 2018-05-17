package edu.gcccd.csis;

import java.util.Iterator;
import java.io.*;

public class MyProject2 implements Project2 {
    NodeList<Integer> sum = new NodeList<>();

    @Override
    public NodeList<Integer> addition(NodeList<Integer> nodeList1, NodeList<Integer> nodeList2) {

        nodeList1 = reverse(nodeList1.iterator());
        nodeList2 = reverse(nodeList2.iterator());
        Iterator<Integer> iter1 = nodeList1.iterator();
        Iterator<Integer> iter2 = nodeList2.iterator();
        NodeList<Integer> rtn = new NodeList<>();

        int div = 0;
        while(iter1.hasNext() || iter2.hasNext()){
            Integer a;
            Integer b;
            if(!iter1.hasNext()){
                a = 0;
            }else {a = iter1.next();}
            if(!iter2.hasNext()){
                b = 0;
            }else{b = iter2.next();}
            int c = (a + b + div) % 10;
            div = (a + b + div) / 10;
            rtn.append(c);
        }
        rtn = reverse(rtn.iterator());
        return rtn;
    }

    public static NodeList<Integer> reverse(Iterator<Integer> a) {
        NodeList<Integer> t = new NodeList<>();
        Integer x = a.next();
        if (a.hasNext()) {
            t = reverse(a);
        }
        t.append(x);
        return t;
    }

    @Override
    public NodeList<Integer> addition(Iterator<NodeList<Integer>> iterator) {

        NodeList<Integer> n1 = iterator.next();
        if(sum.getLength() == 0){
            sum.append(0);
        }
        sum = addition(sum, n1);

        if(iterator.hasNext()){
            addition(iterator);
        }

        return sum;
    }

    @Override
    public void save(NodeList<Integer> nodeList, String fileName) {

        Iterator<Integer> iter = nodeList.iterator();
        try(OutputStream os = new FileOutputStream(fileName)){
            while(iter.hasNext()) {
                os.write(iter.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public NodeList<Integer> load(String fileName) {
        NodeList<Integer> rtn = new NodeList<>();
        try(FileInputStream fis = new FileInputStream(fileName)){
            int size = fis.available();
            for(int i = 0; i < size; i++){
                rtn.append(fis.read());
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e2){
            e2.printStackTrace();
        }
        return rtn ;
    }

    public static void main(final String[] args) {
        final int L = 30;

        final NodeList<Integer> n1 = Project2.generateNumber(L); // (head 1st) e.g. 3457
        final NodeList<Integer> n2 = Project2.generateNumber(L); // (head 1st) e.g. 682
        System.out.println("Reached check level one");
        final Project2 p = new MyProject2();
        System.out.println("Reached check level two");
        Project2.print(p.addition(n1, n2)); //  n1+n2, e.g. 4139
        System.out.println("Reached check level three");
        final NodeList<NodeList<Integer>> listOfLists = new NodeList<>();
        for (int i = 0; i < L; i++) {
            listOfLists.append(Project2.generateNumber(L));
        }
        System.out.println("Reached check level four");
        p.save(p.addition(listOfLists.iterator()), "result.bin");
        System.out.println("Reached check level five");
        Project2.print(p.load("result.bin"));
    }
}