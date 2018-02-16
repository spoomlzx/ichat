package com.lan.ichat.dao;

import com.lan.ichat.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {

    Admin getAdminByUsername(@Param("username") String username);

    int insert(@Param("admin") Admin admin);

    int update(@Param("admin") Admin admin);
}
