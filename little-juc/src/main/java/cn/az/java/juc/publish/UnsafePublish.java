package cn.az.java.juc.publish;

import java.util.Arrays;

/**
 * @author az
 * @date 4/5/2020
 */
public class UnsafePublish {

    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        System.out.println(Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";

        System.out.println(Arrays.toString(unsafePublish.getStates()));
    }
}
