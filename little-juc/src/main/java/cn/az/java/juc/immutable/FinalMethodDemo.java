package cn.az.java.juc.immutable;

/**
 * 描述：     final的方法
 * @author az
 */
public class FinalMethodDemo {

    public void drink() {

    }

    public final void eat() {

    }

    public static void sleep() {

    }

    static class SubClass extends FinalMethodDemo {

        @Override
        public void drink() {
            super.drink();
            eat();
        }

        //    public final void eat() {
//
//    }
        public static void sleep() {

        }
    }

}

