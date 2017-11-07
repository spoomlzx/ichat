package com.lan.ichat.dao;

import com.farsunset.cim.sdk.server.session.CIMSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SessionMapper {

    CIMSession getByAccount(@Param("account") String account);

    List<CIMSession> getSessionList();

    int insert(@Param("session") CIMSession session);

    int update(@Param("session") CIMSession session);

    int delete(@Param("account") String account);
}

