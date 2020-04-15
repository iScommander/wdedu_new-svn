package com.utils;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单的缓存模型
 * 
 * @author qianqing
 * 
 */
public class SimpleCached implements Cache {

    private static SimpleCached instance;

    /**
     * 缓存数据索引
     */
    private Map<String, LastCache> cache = new ConcurrentHashMap<String, LastCache>();

    /**
     * 缓存超时时间，单位：毫秒
     */
    private Long expired = 0L;

    public SimpleCached() {
        this(5 * 1000 * 60L);
    }

    public SimpleCached(Long expired) {
        this.expired = expired;
    }

    public static synchronized SimpleCached getInstance() {
        if (instance == null) {
            instance = new SimpleCached();
        }
        return instance;
    }

    @Override
    public void refresh(String key, Object target) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
        cache.put(key, new LastCache(target));
    }

    @Override
    public Object getCache(String key) {
        if (!this.exist(key)) {
            return null;
        }

        return cache.get(key).getData();
    }

    @Override
    public Boolean isExpired(String key) {
        if (!this.exist(key)) {
            return null;
        }

        long currtime = new Date().getTime();
        long lasttime = cache.get(key).getRefreshtime();

        return (currtime - lasttime) > expired;
    }

    @Override
    public void setExpired(Long millsec) {
        this.expired = millsec;
    }

    @Override
    public Boolean exist(String key) {
        return cache.containsKey(key);
    }

}
