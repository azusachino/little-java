package cn.az.java.collections;

import java.util.PriorityQueue;

/**
 * @author az
 */
public class PriorityQueueDemo {

    public static void main(String[] args) {

        PriorityQueue<Integer> integerQueue = new PriorityQueue<>();

        integerQueue.add(1);
        integerQueue.add(1);
        integerQueue.add(2);
        integerQueue.add(3);

        integerQueue.forEach(System.out::println);

    }
}
