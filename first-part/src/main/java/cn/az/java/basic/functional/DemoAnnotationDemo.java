package cn.az.java.basic.functional;

@DemoAnnotation(value = "hello")
public class DemoAnnotationDemo {

    public static void main(String[] args) {
        DemoAnnotation demoAnnotation = DemoAnnotationDemo.class.getAnnotation(DemoAnnotation.class);
        System.out.println(demoAnnotation.value());
    }
}
