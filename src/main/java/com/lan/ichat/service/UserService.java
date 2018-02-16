package com.lan.ichat.service;

import com.lan.ichat.model.Friend;
import com.lan.ichat.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getUserList();

    User getUserByUsername(String username);

    User getUserById(Long id);

    Map<String, Friend> getFriendList(Long id);

    int insertFriend(Long userId, Long friendId);

    int updateRemark(Long userId, Long friendId, String remark);

    int updateMomentSetting(Long userId, Long friendId, Integer hideMyMM, Integer hideHisMM);

    int updateStar(Long userId, Long friendId, int star);

    int insert(User user);

    int insertList(List<User> users);

    int update(User user);

    int delete(Long id);
}
