package com.lan.ichat.service;

import com.lan.ichat.model.UserEntity;
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
public class TokenService implements BaseService<UserEntity> {
    /**
     * console默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60;
    /**
     * 不设置过期时长，主要用于ichat客户端的登录
     */
    public final static long NOT_EXPIRE = -1L;
    @Autowired
    private RedisTemplate<String, UserEntity> redisTemplate;

    @Override
    public void set(String token, UserEntity userEntity) {
        this.redisTemplate.boundValueOps(token).set(userEntity);
        this.redisTemplate.expire(token, DEFAULT_EXPIRE, TimeUnit.SECONDS);
    }

    public void set(String token, UserEntity userEntity, Long expire) {
        this.redisTemplate.boundValueOps(token).set(userEntity);
        if (expire != NOT_EXPIRE) {
            this.redisTemplate.expire(token, DEFAULT_EXPIRE, TimeUnit.SECONDS);
        }
    }

    @Override
    public UserEntity get(String token) {
        return (UserEntity) this.redisTemplate.boundValueOps(token).get();
    }

    @Override
    public void delete(String token) {
        this.redisTemplate.delete(token);
    }
}
