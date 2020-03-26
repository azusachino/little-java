module first.part {
    // import java.sql.Connection;
    // require java.sql;
    requires java.base;
    requires java.logging;
    requires java.desktop;
    requires java.net.http;
    requires spring.core;
    exports cn.az.java.basic.demo;
    exports cn.az.java.basic; // must contain at least one Class
}
