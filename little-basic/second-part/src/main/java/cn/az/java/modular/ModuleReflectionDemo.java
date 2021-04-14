package cn.az.java.modular;

import java.lang.module.ModuleDescriptor;

/**
 * @author az
 */
public class ModuleReflectionDemo {

    public static void main(String[] args) {

        Class<?> klass = ModuleReflectionDemo.class;

        Module module = klass.getModule();

        String moduleName = module.getName();

        System.out.printf("类 %s 存在于模块 : %s 之中\n", klass.getName(), moduleName);

        ModuleDescriptor moduleDescriptor = module.getDescriptor();

        moduleDescriptor.requires().forEach(requires -> {
                System.out.printf("requires 模块名称 %s，修饰符定义： %s \n", requires.name(), requires.modifiers());
            }
        );

        moduleDescriptor.exports().forEach(exports -> {
            System.out.printf("exports 包名称 %s，targets： %s \n", exports.source(), exports.targets());
        });
    }
}
