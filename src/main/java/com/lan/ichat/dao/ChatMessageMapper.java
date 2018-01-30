package com.lan.ichat.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spoom.im.sdk.server.model.ChatMessage;

import java.util.List;

@Mapper
public interface ChatMessageMapper {
    int insert(@Param("chatMessage") ChatMessage chatMessage);

    int insertList(@Param("chatMessages") List<ChatMessage> chatMessages);

    int update(@Param("chatMessage") ChatMessage chatMessage);
}
