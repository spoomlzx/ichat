package com.lan.ichat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * package com.lan.ichat.service
 *
 * @author lanzongxiao
 * @date 2017/11/2
 */
@Service
public class TokenService implements BaseService<Object> {
    /**
     * console默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60;
    /**
     * 不设置过期时长，主要用于ichat客户端的登录
     */
    public final static long NOT_EXPIRE = -1L;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(String token, Object obj) {
        // 28/12/2017 TODO 持久化到db
        this.redisTemplate.boundValueOps(getTokenKey(token)).set(obj);
        this.redisTemplate.expire(getTokenKey(token), DEFAULT_EXPIRE, TimeUnit.SECONDS);
    }

    public void set(String token, Object obj, Long expire) {
        this.redisTemplate.boundValueOps(getTokenKey(token)).set(obj);
        if (expire != NOT_EXPIRE) {
            this.redisTemplate.expire(getTokenKey(token), expire, TimeUnit.SECONDS);
        }
    }

    @Override
    public Object get(String token) {
        // 28/12/2017 TODO 若redis中没有，则从db中取
        return this.redisTemplate.boundValueOps(getTokenKey(token)).get();
    }

    @Override
    public void delete(String token) {
        this.redisTemplate.delete(getTokenKey(token));
    }

    private String getTokenKey(String token) {
        return "user_token:" + token;
    }
}
