package com.lan.ichat.service;

import com.farsunset.cim.sdk.server.session.CIMSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * package com.lan.ichat.service
 *
 * @author lanzongxiao
 * @date 2017/11/7
 */
@Service
public class SessionService implements BaseService<CIMSession> {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(String key, CIMSession session) {
        this.redisTemplate.boundValueOps(key).set(session);
    }

    @Override
    public CIMSession get(String key) {
        return (CIMSession) this.redisTemplate.boundValueOps(key).get();
    }

    @Override
    public void delete(String key) {
        this.redisTemplate.delete(key);
    }
}
