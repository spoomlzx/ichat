package com.lan.common.config;

import com.lan.ichat.im.handler.BindHandler;
import com.lan.ichat.im.handler.SessionClosedHandler;
import org.spoom.im.sdk.server.ChannelManager;
import org.spoom.im.sdk.server.IMConstant;
import org.spoom.im.sdk.server.MessageHandler;
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

    /**
     * 注册处理消息的handler
     *
     * @return
     * @throws IOException
     */
    @Bean
    public ChannelManager channelManager() throws IOException {
        ChannelManager manager = new ChannelManager();
        manager.setPort(23456);
        HashMap<Integer, MessageHandler> handlers = new HashMap<>();
        handlers.put(IMConstant.HandlerType.BIND_CLIENT, bindHandler);
        handlers.put(IMConstant.HandlerType.CLOSE_SESSION, sessionClosedHandler);
        manager.setHandlers(handlers);
        manager.bind();
        return manager;
    }
}
