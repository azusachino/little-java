package cn.az.java.algorithm;

/**
 * 排序接口
 * <p>
 * 原地(在地)排序 - In-Place
 *
 * @param <T> the type parameter
 * @author az
 */
public interface Sort<T extends Comparable<T>> {

    /**
     * Sort.
     *
     * @param values the values
     */
    void sort(T[] values);


    /**
     * Of t [ ].
     *
     * @param <T>    the type parameter
     * @param values the values
     * @return the t [ ]
     */
    @SafeVarargs
    static <T> T[] of(T... values) {
        return values;
    }
}
