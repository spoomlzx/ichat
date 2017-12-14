package com.lan.ichat.dao;

import com.lan.ichat.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserEntity> getUserList(@Param("name") String name, @Param("gender") Integer gender);

    UserEntity getUserByUsername(@Param("username") String username, @Param("roleId") Integer roleId);

    UserEntity getUserById(@Param("id") Long id);

    int insert(@Param("userEntity") UserEntity userEntity);

    int insertList(@Param("userEntitys") List<UserEntity> userEntitys);

    int update(@Param("userEntity") UserEntity userEntity);

    int delete(@Param("id") Long id);
}

