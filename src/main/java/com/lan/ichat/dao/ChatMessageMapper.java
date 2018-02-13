package com.lan.ichat.dao;

import com.lan.ichat.model.ChatMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMessageMapper {
    int insert(@Param("chatMessage") ChatMessage chatMessage);

    int insertList(@Param("chatMessages") List<ChatMessage> chatMessages);

    int update(@Param("chatMessage") ChatMessage chatMessage);
}
