package cn.az.boot.app.spi.impl;

import cn.az.boot.app.spi.SaySomething;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @author az
 * @since 09/17/20
 */
@ConditionalOnProperty(prefix = "say", havingValue = "good")
public class SayGoodImpl implements SaySomething {

    /**
     * 说点什么
     */
    @Override
    public void saySomething() {
        System.out.println("Say Good");
    }
}
