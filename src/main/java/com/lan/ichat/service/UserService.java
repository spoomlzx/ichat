package com.lan.ichat.service;

import com.lan.ichat.model.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getUserList();

    UserEntity getUserById(Long id);

    int insert(UserEntity userEntity);

    int insertList(List<UserEntity> userEntitys);

    int update(UserEntity userEntity);
}

