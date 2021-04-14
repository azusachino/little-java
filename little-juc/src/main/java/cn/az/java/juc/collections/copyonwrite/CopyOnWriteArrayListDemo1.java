package cn.az.java.juc.collections.copyonwrite;

import java.util.ArrayList;

/**
 * 描述：演示CopyOnWriteArrayList可以在迭代的过程中修改数组内容，但是ArrayList不行，对比
 *
 * @author az
 */
public class CopyOnWriteArrayListDemo1 {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
//        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        for (String s : list) {
            System.out.println("list is" + list);
            System.out.println(s);

            if (s.equals("2")) {
                list.remove("5");
            }
            if (s.equals("3")) {
                list.add("3 found");
            }
        }
    }
}
