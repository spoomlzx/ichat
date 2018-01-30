package com.lan.ichat.service;

import org.spoom.im.sdk.server.model.ChatMessage;

import java.util.List;
public interface ChatMessageService{

    int insert(ChatMessage chatMessage);

    int insertList(List<ChatMessage> chatMessages);

    int update(ChatMessage chatMessage);
}
