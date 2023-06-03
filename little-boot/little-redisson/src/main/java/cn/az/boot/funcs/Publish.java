package cn.az.boot.funcs;

import cn.az.boot.constant.Const;
import cn.az.boot.model.RedissonObject;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * @author Liz
 */
@Component
public class Publish {
    @Resource
    private RedissonClient redissonClient;

    //发布
    public long publish(RedissonObject obj) {
        RTopic rTopic = redissonClient.getTopic(Const.TOPIC_NAME);
        return rTopic.publish(obj);
    }

}
