package com.lan.common.config;

import com.farsunset.cim.sdk.server.handler.CIMNioSocketAcceptor;
import com.farsunset.cim.sdk.server.handler.CIMRequestHandler;
import com.lan.ichat.im.handler.BindHandler;
import com.lan.ichat.im.handler.SessionClosedHandler;
import com.lan.ichat.im.manager.IChatSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;

/**
 * package com.lan.common.config
 *
 * @author lanzongxiao
 * @date 2017/11/3
 */
@Configuration
public class IChatConfig {
    @Autowired
    private BindHandler bindHandler;
    @Autowired
    private SessionClosedHandler sessionClosedHandler;
    @Autowired
    private IChatSessionManager sessionManager;

    /**
     * 由于bindHandler中使用了sessionManager，故只能在CIMNioSocketAcceptor初始化完成之后注入给sessionManager
     *
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod = "unbind")
    public CIMNioSocketAcceptor iChatNioAcceptor() throws IOException {
        CIMNioSocketAcceptor acceptor = new CIMNioSocketAcceptor();
        acceptor.setPort(23456);
        HashMap<String, CIMRequestHandler> handlers = new HashMap<>();
        handlers.put("client_bind", bindHandler);
        handlers.put("client_closed", sessionClosedHandler);
        acceptor.setHandlers(handlers);
        acceptor.bind();
        sessionManager.setCimNioSocketAcceptor(acceptor);
        return acceptor;
    }
}
