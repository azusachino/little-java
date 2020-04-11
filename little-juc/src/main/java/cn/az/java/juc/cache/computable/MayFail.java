package cn.az.java.juc.cache.computable;

import cn.hutool.core.util.RandomUtil;

import java.io.IOException;

/**
 * 描述：     耗时计算的实现类，有概率计算失败
 *
 * @author az
 */
public class MayFail implements Computable<String, Integer> {

    @Override
    public Integer compute(String arg) throws Exception {

        if (RandomUtil.randomInt() > 0) {
            throw new IOException("读取文件出错");
        }
        Thread.sleep(3000);
        return Integer.valueOf(arg);
    }
}
