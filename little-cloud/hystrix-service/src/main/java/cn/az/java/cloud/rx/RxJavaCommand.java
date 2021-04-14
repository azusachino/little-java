package cn.az.java.cloud.rx;

import rx.Observable;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 * @author az
 * @since 2020-04-14
 */
public class RxJavaCommand {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程：" + Thread.currentThread().getName());
        demoStandardReactive();
    }

    private static void demoStandardReactive() throws InterruptedException {

        List<Integer> values = Arrays.asList(1, 2, 3);
        //发布多个数据
        Observable.from(values)
            // 在 newThread 线程执行
            .subscribeOn(Schedulers.newThread())
            .subscribe(value -> {
                if (value > 2) {
                    throw new IllegalStateException("数据不应许大于 2");
                }
                //消费数据
                println("消费数据：" + value);
            }, e -> {
                // 当异常情况，中断执行
                println("发生异常 , " + e.getMessage());
            }, () -> {
                // 当整体流程完成时
                println("流程执行完成");
            });

        // 等待线程执行完毕
        Thread.sleep(100);
    }

    private static void demoObservable() throws InterruptedException {

        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //发布多个数据
        Observable.from(values)
            // 在 computation 线程执行
            .subscribeOn(Schedulers.computation())
            // 订阅并且消费数据
            .subscribe(RxJavaCommand::println)
        ;

        // 等待线程执行完毕
        Thread.sleep(100);

    }

    private static void demoSingle() {

        // 仅能发布单个数据
        Single.just("Hello,World")
            // 在 I/O 线程执行
            .subscribeOn(Schedulers.io())
            // 订阅并且消费数据
            .subscribe(RxJavaCommand::println)
        ;
    }

    private static void println(Object value) {

        System.out.printf("[线程: %s] %s\n", Thread.currentThread().getName(), value);

    }
}
