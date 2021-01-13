package cn.az.boot.app.spi;

/**
 * @author az
 * @since 09/17/20
 */
public interface SaySomething {

    /**
     * 说点什么
     */
    void saySomething();

    /**
     * hello
     */
    default void sayHello() {
        System.out.println("Hello World");
    }
}
