package com.lan.ichat.service;

import org.spoom.im.sdk.server.IMSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * package com.lan.ichat.service
 *
 * @author lanzongxiao
 * @date 2017/11/7
 */
@Service
public class SessionService implements BaseService<IMSession> {

    private HashMap<String, IMSession> localSessions = new HashMap<>();

    @Override
    public void set(String key, IMSession session) {
        localSessions.put(key, session);
    }

    @Override
    public IMSession get(String key) {
        return localSessions.get(key);
    }

    @Override
    public void delete(String key) {
        localSessions.remove(key);
    }
}
