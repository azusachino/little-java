package cn.az.java.basic.demo;

import java.util.Map;
import java.util.logging.Logger;

public class ModularDemo {


    public static void main(String[] args) {
//        Connection connection = null;
        // Logging API 通过 java.sql 传递依赖
        Logger logger = Logger.getLogger("abc");
        var s = "aw";
        var m = Map.of("1", 2, 3, "4");
        m.forEach((v, k) -> {
            System.out.println(String.valueOf(v) + k);
        });

    }
}
