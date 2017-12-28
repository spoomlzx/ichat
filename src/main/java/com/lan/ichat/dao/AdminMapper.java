package com.lan.ichat.dao;

import com.lan.ichat.model.AdminEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {

    AdminEntity getAdminByUsername(@Param("username") String username);

    int insert(@Param("adminEntity") AdminEntity adminEntity);

    int update(@Param("adminEntity") AdminEntity adminEntity);
}
