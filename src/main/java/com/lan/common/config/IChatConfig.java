package com.lan.common.config;

import com.lan.ichat.im.handler.BindHandler;
import com.lan.ichat.im.handler.ChatMessageReceivedHandler;
import com.lan.ichat.im.handler.SessionClosedHandler;
import com.lan.ichat.im.manager.MessageDispatcher;
import org.spoom.im.sdk.server.ChannelManager;
import org.spoom.im.sdk.server.IMConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

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
    private ChatMessageReceivedHandler chatMessageReceivedHandler;
    @Autowired
    private MessageDispatcher messageDispatcher;

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
        initDispatcher();
        manager.setDispatcher(messageDispatcher);
        manager.bind();
        return manager;
    }

    private void initDispatcher() {
        messageDispatcher.put(IMConstant.MessageAction.ACTION_LOGIN, bindHandler);
        messageDispatcher.put(IMConstant.MessageAction.ACTION_LOGOUT, sessionClosedHandler);
        messageDispatcher.put(IMConstant.MessageAction.ACTION_MESSAGE_RECEIVED, chatMessageReceivedHandler);
    }
}
