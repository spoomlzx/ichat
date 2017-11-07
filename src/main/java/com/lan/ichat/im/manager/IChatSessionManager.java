package com.lan.ichat.im.manager;

import com.farsunset.cim.sdk.server.session.CIMSession;
import com.farsunset.cim.sdk.server.session.SessionManager;
import com.lan.common.util.StringUtils;
import com.lan.ichat.dao.SessionMapper;
import com.lan.ichat.service.SessionService;
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
    public void add(CIMSession session) {
        session.setGid(StringUtils.getUUID());
        sessionMapper.delete(session.getAccount());
        sessionMapper.insert(session);
        String key = CACHE_PREFIX + session.getAccount();
        sessionService.set(key, session);
    }

    @Override
    public void update(CIMSession session) {
        sessionMapper.update(session);
        String key = CACHE_PREFIX + session.getAccount();
        sessionService.set(key, session);
    }

    @Override
    public CIMSession get(String account) {
        String key = CACHE_PREFIX + account;
        CIMSession session = sessionService.get(key);
        if (session == null) {
            session = sessionMapper.getByAccount(account);
            sessionService.set(key, session);
        }
        return session;
    }

    @Override
    public List<CIMSession> queryAll() {
        List<CIMSession> sessions = sessionMapper.getSessionList();
        return sessions;
    }

    @Override
    public void remove(String account) {
        String key = CACHE_PREFIX + account;
        sessionMapper.delete(account);
        sessionService.delete(key);
    }

}
