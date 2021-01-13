package cn.az.java.spring.other;

import java.util.Deque;
import java.util.LinkedList;

/**
 * DequeDemo
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see Deque
 * @since 2020-03-23
 */
public class DequeDemo {

    public static void main(String[] args) {

        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(1);
        deque.addLast(2);
        System.out.println(deque);

        int a = deque.getFirst();
        int b = deque.peekLast();

        while (deque.size() > 0) {
            System.out.println(deque.pollLast());
        }
    }
}
