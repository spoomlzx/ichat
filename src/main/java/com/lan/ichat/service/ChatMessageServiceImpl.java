package com.lan.ichat.service;

import com.lan.ichat.dao.ChatMessageMapper;
import org.spoom.im.sdk.server.model.ChatMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService{

    @Resource
    private ChatMessageMapper chatMessageMapper;

    @Override
    @Transactional
    public int insert(ChatMessage chatMessage){
        try {
            return chatMessageMapper.insert(chatMessage);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int insertList(List<ChatMessage> chatMessages){
        try {
            return chatMessageMapper.insertList(chatMessages);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int update(ChatMessage chatMessage){
        try {
            return chatMessageMapper.update(chatMessage);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
