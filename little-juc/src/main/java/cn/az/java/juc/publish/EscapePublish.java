package cn.az.java.juc.publish;

/**
 * @author az
 * @date 4/5/2020
 */
public class EscapePublish {

    private int escape = 0;

    public EscapePublish() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            // 0 (can be seen by inner class)
            System.out.println(EscapePublish.this.escape);
        }
    }

    public static void main(String[] args) {
        new EscapePublish();
    }
}
