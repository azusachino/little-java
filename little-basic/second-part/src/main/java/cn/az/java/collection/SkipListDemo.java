package cn.az.java.collection;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * SkipListDemo
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see java.util.concurrent.ConcurrentSkipListSet
 * @since 2020-03-23
 */
public class SkipListDemo {

    public static void main(String[] args) {
        ConcurrentSkipListSet<? super CharSequence> skipListSet = new ConcurrentSkipListSet<>(Comparator.comparingInt(CharSequence::length));
        skipListSet.add("a");

        System.out.println(skipListSet);
    }
}
