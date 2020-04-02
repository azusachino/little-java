package cn.az.java.juc.threadlocal;

/**
 * 描述：     演示ThreadLocal用法2：避免传递参数的麻烦
 * @author az
 */
public class ThreadLocalNormalUsage06 {

    public static void main(String[] args) {
        new Service1().process("");

    }

    static class Service1 {

        public void process(String name) {
            User user = new User("超哥");
            UserContextHolder.holder.set(user);
            new Service2().process();
        }
    }

    static class Service2 {

        public void process() {
            User user = UserContextHolder.holder.get();
            ThreadLocalNormalUsage05.ThreadSafeFormatter.dateFormatThreadLocal.get();
            System.out.println("Service2拿到用户名：" + user.name);
            new Service3().process();
        }
    }

    static class Service3 {

        public void process() {
            User user = UserContextHolder.holder.get();
            System.out.println("Service3拿到用户名：" + user.name);
            UserContextHolder.holder.remove();
        }
    }

    static class UserContextHolder {
        public static ThreadLocal<User> holder = new ThreadLocal<>();
    }

    static class User {

        String name;

        public User(String name) {
            this.name = name;
        }
    }

}

