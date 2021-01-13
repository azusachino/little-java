package cn.az.java.thread;

/**
 * @author az
 */
public class DumpThreadDemo {

    public static void main(String[] args) {

        // Throwable API
        new Throwable("Stack trace").printStackTrace(System.out);

        // Thread API
        Thread.dumpStack();

        // Java 9 StackWalker API
        StackWalker stackWalker = StackWalker.getInstance();
        stackWalker.forEach(System.out::println);
    }
}
