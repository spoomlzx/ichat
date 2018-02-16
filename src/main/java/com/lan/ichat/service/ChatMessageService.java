package com.lan.ichat.service;

import com.lan.ichat.model.ChatMessage;

import java.util.List;

public interface ChatMessageService {

    int insert(ChatMessage chatMessage);

    int insertList(List<ChatMessage> chatMessages);

    int update(String msgId, Integer status);
}
