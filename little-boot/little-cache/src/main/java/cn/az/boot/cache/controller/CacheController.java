package cn.az.boot.cache.controller;

import cn.hutool.core.map.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author az
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheManager cacheManager;

    @PostMapping("/save")
    public Map<String, Object> save(@RequestParam String key, @RequestParam String value) {

        Cache cache = cacheManager.getCache("redis");
        Assert.notNull(cache, "no existing cache");
        cache.put(key, value);
        Map<String, Object> map = MapUtil.newHashMap();
        map.put(key, value);
        return map;
    }
}
