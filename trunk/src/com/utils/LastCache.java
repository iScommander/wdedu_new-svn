package com.utils;

import java.util.Date;

/**
 * 缓存实体
 * 
 * @author qianqing
 * 
 */
public class LastCache {

    /**
     * 上次缓存的数据
     */
    private Object data;

    /**
     * 最后刷新时间
     */
    private long refreshtime;

    public LastCache(Object data) {
        this(data, new Date().getTime());
    }

    public LastCache(Object data, long refreshtime) {
        this.data = data;
        this.refreshtime = refreshtime;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getRefreshtime() {
        return refreshtime;
    }

    public void setRefreshtime(long refreshtime) {
        this.refreshtime = refreshtime;
    }
}
