/**
 * @author az
 * @date 2020/4/8
 */

module little.juc {
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

    exports cn.az.java.juc;
}
