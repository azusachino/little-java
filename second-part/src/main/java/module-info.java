/*

module second.part {
    // requires 内部依赖
    // 命名模块
    // artifact 资源根 package 存有 module-info.class（包含模块名称）
    requires java.base; // 默认依赖
    requires java.sql;  // 传递依赖 requires transitive
    requires java.compiler;  // exports 控制可访问的 API 包

    // 非命名模块
    requires transitive spring.context;
    requires transitive org.apache.commons.lang3;  // 规律：
    // Maven artifactId（jar或者war文件）中横划（画）线 "-"
    // 在模块化名称使用 "."
    requires transitive commons.collections;
    requires transitive lombok;

//    uses cn.az.java.MySpi; // JAVA SPI 实现

    // exports 供外部访问
    exports cn.az.java.modular;
}

*/
