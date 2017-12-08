package com.lan.ichat.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spoom.im.sdk.server.IMSession;

import java.util.List;

@Mapper
public interface SessionMapper {
    IMSession getByAccount(@Param("account") String account);

    List<IMSession> getSessionList();

    int insert(@Param("iMSession") IMSession iMSession);

    int update(@Param("iMSession") IMSession iMSession);

    int delete(@Param("account") String account);
}

