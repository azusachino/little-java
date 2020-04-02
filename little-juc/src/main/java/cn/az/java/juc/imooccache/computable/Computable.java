package cn.az.java.juc.imooccache.computable;

/**
 * 描述：     有一个计算函数computer，用来代表耗时计算，每个计算器都要实现这个接口，这样就可以无侵入实现缓存功能
 *
 * @param <A> the type parameter
 * @param <V> the type parameter
 * @author az
 */
public interface Computable<A, V> {

    /**
     * Compute v.
     *
     * @param arg the arg
     * @return the v
     * @throws Exception the exception
     */
    V compute(A arg) throws Exception;
}
