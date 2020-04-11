package cn.az.java.juc.cache;

import cn.az.java.juc.cache.computable.Computable;
import cn.az.java.juc.cache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：     用装饰者模式，给计算器自动添加缓存功能
 * @author az
 */
public class ImoocCache2<A, V> implements Computable<A, V> {

    private final Map<A, V> cache = new HashMap<>(16);

    private final Computable<A, V> c;

    public ImoocCache2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws Exception {
        V result = cache.get(arg);
        if (result == null) {
            System.out.println("进入缓存机制");
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        ImoocCache2<String, Integer> expensiveComputer = new ImoocCache2<>(
                new ExpensiveFunction());
        Integer result = expensiveComputer.compute("666");
        System.out.println("第一次计算结果：" + result);
        result = expensiveComputer.compute("666");
        System.out.println("第二次计算结果：" + result);
    }
}
