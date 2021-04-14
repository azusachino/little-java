/**
 *
 */

module little.juc {
    requires java.base;

    requires lombok;
    requires spring.jcl;
    requires hutool.core;
    requires com.google.common;
    requires org.apache.tomcat.embed.core;
    requires spring.web;
    requires spring.context;
    requires spring.boot;
    requires spring.webmvc;
    requires spring.boot.autoconfigure;
    requires org.slf4j;

    exports cn.az.java.juc;
}
