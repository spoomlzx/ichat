package com.lan.ichat.service;

import com.farsunset.cim.sdk.server.session.CIMSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * package com.lan.ichat.service
 *
 * @author lanzongxiao
 * @date 2017/11/7
 */
@Service
public class SessionService implements BaseService<CIMSession> {

    private HashMap<String, CIMSession> localSessions = new HashMap<>();

    @Override
    public void set(String key, CIMSession session) {
        localSessions.put(key, session);
    }

    @Override
    public CIMSession get(String key) {
        return localSessions.get(key);
    }

    @Override
    public void delete(String key) {
        localSessions.remove(key);
    }
}
