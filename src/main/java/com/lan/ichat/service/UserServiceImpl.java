package com.lan.ichat.service;

import com.lan.ichat.dao.UserMapper;
import com.lan.ichat.model.UserEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserEntity> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public UserEntity getUserByUsername(String username, Integer roleId) {
        return userMapper.getUserByUsername(username, roleId);
    }

    @Override
    public UserEntity getUserById(Long id) {
        UserEntity user = userMapper.getUserById(id);
        return user;
    }

    public int insert(UserEntity userEntity) {
        return userMapper.insert(userEntity);
    }

    public int insertList(List<UserEntity> userEntitys) {
        return userMapper.insertList(userEntitys);
    }

    public int update(UserEntity userEntity) {
        return userMapper.update(userEntity);
    }
}

