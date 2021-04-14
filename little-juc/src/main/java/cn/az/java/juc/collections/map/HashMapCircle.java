package cn.az.java.juc.collections.map;

import cn.hutool.core.thread.ThreadUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * before jdk1.8
 *
 * @author az
 * @since 2020-04-09
 */
public class HashMapCircle {

    private static Map<Integer, String> map = new HashMap<>(2, 1.5F);

    public static void main(String[] args) throws Exception {
        ExecutorService service = ThreadUtil.newExecutor(10);

        map.put(7, "a");
        map.put(5, "b");
        map.put(3, "c");

        service.execute(() -> {
            map.put(15, "d");
        });
        service.execute(() -> {
            map.put(1, "e");
        });

        System.out.println(map);
        service.awaitTermination(6, TimeUnit.SECONDS);

        service.shutdown();
    }
}
