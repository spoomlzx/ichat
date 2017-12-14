package com.lan.ichat.im.manager;

import com.lan.ichat.dao.SessionMapper;
import com.lan.ichat.service.SessionService;
import org.spoom.im.sdk.server.IMSession;
import org.spoom.im.sdk.server.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * package com.lan.ichat.im.manager
 *
 * @author lanzongxiao
 * @date 2017/11/4
 */
@Service
public class IChatSessionManager implements SessionManager {

    private static final String CACHE_PREFIX = "ichat_session_";
    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionMapper sessionMapper;


    @Override
    public void add(IMSession session) {
        sessionMapper.delete(session.getAccount());
        sessionMapper.insert(session);
        String key = CACHE_PREFIX + session.getAccount();
        sessionService.set(key, session);
    }

    @Override
    public void update(IMSession session) {
        sessionMapper.update(session);
        String key = CACHE_PREFIX + session.getAccount();
        sessionService.set(key, session);
    }

    @Override
    public IMSession get(String account) {
        String key = CACHE_PREFIX + account;
        return sessionService.get(key);
    }

    @Override
    public List<IMSession> queryAll() {
        return sessionMapper.getSessionList();
    }

    @Override
    public void remove(String account) {
        String key = CACHE_PREFIX + account;
        sessionMapper.delete(account);
        sessionService.delete(key);
    }
}
