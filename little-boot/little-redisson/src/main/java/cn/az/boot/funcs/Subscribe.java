package cn.az.boot.funcs;

import cn.az.boot.constant.Const;
import cn.az.boot.model.RedissonObject;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Liz
 * @date 1/16/2020
 */
@Slf4j
@Component
public class Subscribe {

    @Resource
    private RedissonClient redissonClient;

    @PostConstruct
    public void subscribe() {
        RTopic rTopic = redissonClient.getTopic(Const.TOPIC_NAME);
        // 接受订阅的消息
        rTopic.addListener(RedissonObject.class, (cs, obj) -> {
            log.info("接受到消息主题={}，内容={}", cs, obj);
            System.out.println("传输的数据为=" + obj);
        });
    }
}
