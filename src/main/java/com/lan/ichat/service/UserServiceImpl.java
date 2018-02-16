package com.lan.ichat.service;

import com.lan.ichat.dao.UserMapper;
import com.lan.ichat.model.Friend;
import com.lan.ichat.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Map<String, Friend> getFriendList(Long id) {
        List<User> users = userMapper.getFriendList(id);
        Map<String,Friend> friendMap=new HashMap<>();
        for (User user : users) {
            Friend friend = new Friend();
            int hideMyMM = user.getHideMyMM();
            int hideHisMM = user.getHideHisMM();
            int star = user.getStar();
            int blacklist = user.getBlacklist();
            int chatroom = user.getChatroom();
            int type = (star << 6) | (blacklist << 5) | (hideHisMM << 4) | (hideMyMM << 3) | (chatroom << 2) | 3;
            friend.setId(user.getId());
            friend.setUsername(user.getUsername());
            friend.setNickname(user.getNickname());
            friend.setType(type);
            friend.setTelephone(user.getTelephone());
            friend.setMotto(user.getMotto());
            friend.setAvatar(user.getAvatar());
            friend.setGender(user.getGender());
            friend.setRegion(user.getRegion());
            friend.setRemark(user.getRemark());
            friendMap.put(user.getUsername(),friend);
        }
        return friendMap;
    }

    @Override
    @Transactional
    public int insertFriend(Long userId, Long friendId) {
        try {
            return userMapper.insertFriend(userId, friendId, 0, 0, 0, 0, 0);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int updateRemark(Long userId, Long friendId, String remark) {
        try {
            return userMapper.updateRemark(userId, friendId, remark);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int updateMomentSetting(Long userId, Long friendId, Integer hideMyMM, Integer hideHisMM) {
        try {
            return userMapper.updateMomentSetting(userId, friendId, hideMyMM, hideHisMM);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }



    @Override
    @Transactional
    public int updateStar(Long userId, Long friendId, int star) {
        try {
            return userMapper.updateStar(userId, friendId, star);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int insert(User user) {
        try {
            return userMapper.insert(user);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int insertList(List<User> users) {
        try {
            return userMapper.insertList(users);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public int update(User user) {
        try {
            return userMapper.update(user);
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
