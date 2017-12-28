package com.lan.ichat.service;

import com.lan.ichat.model.UserEntity;

import java.util.HashMap;
import java.util.List;

public interface UserService {

    HashMap<String, Object> getUserList(Integer page, Integer limit, String name, Integer gender);

    UserEntity getUserByChatId(String chatId);

    int insert(UserEntity userEntity);

    int insertList(List<UserEntity> userEntitys);

    int update(UserEntity userEntity);

    int delete(Long id);
}

