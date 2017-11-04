package com.lan.ichat.service;

import com.farsunset.cim.sdk.server.constant.CIMConstant;
import com.farsunset.cim.sdk.server.session.CIMSession;
import com.farsunset.cim.sdk.server.session.SessionManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * package com.lan.ichat.im.manager
 *
 * @author lanzongxiao
 * @date 2017/11/4
 */
@Service
public class IChatSessionManager implements SessionManager {

    private static HashMap<String, CIMSession> sessions = new HashMap<>();
    private static final AtomicInteger connectionsCounter = new AtomicInteger(0);

    public void add(CIMSession session) {
        if (session != null) {
            session.setAttribute(CIMConstant.SESSION_KEY, session.getAccount());
            sessions.put(session.getAccount(), session);
            connectionsCounter.incrementAndGet();
        }
    }

    public CIMSession get(String account) {
        return sessions.get(account);
    }

    public List<CIMSession> queryAll() {
        List<CIMSession> list = new ArrayList<CIMSession>();
        list.addAll(sessions.values());
        return list;
    }

    public void remove(CIMSession session) {
        sessions.remove(session.getAttribute(CIMConstant.SESSION_KEY));
    }

    public void remove(String account) {
        sessions.remove(account);
    }

    public boolean containsCIMSession(String account) {
        return sessions.containsKey(account);
    }

    @Override
    public void update(CIMSession session) {
        sessions.put(session.getAccount(), session);
    }
}
