package cn.az.java.thread;

/**
 * @author az
 */
public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        // Thread 实现 Runnable
        // 如果没有传递 Runnable 对象实现，空执行
        Thread thread = new Thread(ThreadDemo::sayHelloWorld);
        thread.start(); // 启动线程
        thread.join();  // 等待线程结束
        System.out.println("Hello Next...");
        System.out.print(thread.getState());
        ThreadRunnable tr = new ThreadRunnable();
        Thread t2 = new Thread(tr);

        t2.start();
        t2.wait();
        tr.setStop(true);
    }

    public static void sayHelloWorld() {
        System.out.printf("线程 [Id : %s] : Hello,World!\n", Thread.currentThread().getId());
    }


    public static class ThreadRunnable implements Runnable {

        private boolean stop = false;

        public boolean isStop() {
            return stop;
        }

        public void setStop(boolean stop) {
            this.stop = stop;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {

            while (!stop) {
                try {
                    Thread.sleep(5 * 1000L);
                    System.out.println(ThreadRunnable.class.getName() + "is running");
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }

    }
}
