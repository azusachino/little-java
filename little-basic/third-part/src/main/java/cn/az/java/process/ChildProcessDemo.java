package cn.az.java.process;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * @author az
 */
public class ChildProcessDemo {

    public static void main(String[] args) throws IOException {

        // IDEA(主进程) -> 启动 ChildProcessDemo -> Windows 计算器（calc）
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();

        if (operatingSystemMXBean.getName().startsWith("Windows")) {
            // 启动计算器
            Runtime.getRuntime().exec("calc");

//            Process process = Runtime.getRuntime().exec("dir");
//
//            InputStream inputStream = process.getInputStream();
//            int data = 0;
//            while ((data = inputStream.read()) > -1) {
//                System.out.print(data);
//            }
        }

    }
}
