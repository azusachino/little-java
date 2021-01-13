package cn.az.java.basic.functional.stream;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author az
 */
public class StreamCollectDemo {

    public static void main(String[] args) {

        List<Integer> values = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.toList());

        System.out.println(values.getClass());

        values = Stream.of(1, 2, 3, 4, 5)
                .collect(LinkedList::new, List::add, List::addAll);

        System.out.println(values.getClass());
    }
}
