package com.lan.ichat.service;

import com.lan.ichat.dao.UserMapper;
import com.lan.ichat.model.FriendEntity;
import com.lan.ichat.model.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserEntity> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<FriendEntity> getFriendList(Long id) {
        List<UserEntity> users = userMapper.getFriendList(id);
        List<FriendEntity> friends = new ArrayList<>();
        for (UserEntity user : users) {
            FriendEntity friend = new FriendEntity();
            int hideMyMM = user.getHideMyMM();
            int hideHisMM = user.getHideHisMM();
            int star = user.getStar();
            int blacklist = user.getBlacklist();
            int chatroom = user.getChatroom();
            int type = (star << 6) + (blacklist << 5) + (hideHisMM << 4) + (hideMyMM << 3) + (chatroom << 2) + 3;
            friend.setUsername(user.getUsername());
            friend.setNickname(user.getNickname());
            friend.setType(type);
            friend.setTelephone(user.getTelephone());
            friend.setMotto(user.getMotto());
            friend.setAvatar(user.getAvatar());
            friend.setGender(user.getGender());
            friend.setRegion(user.getRegion());
            friend.setEmail(user.getEmail());
            friends.add(friend);
        }
        return friends;
    }

    @Override
    @Transactional
    public int insert(UserEntity userEntity) {
        try {
            return userMapper.insert(userEntity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int insertList(List<UserEntity> userEntitys) {
        try {
            return userMapper.insertList(userEntitys);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int update(UserEntity userEntity) {
        try {
            return userMapper.update(userEntity);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int delete(Long id) {
        try {
            return userMapper.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
