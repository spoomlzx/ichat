package com.lan.ichat.dao;

import com.lan.ichat.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getUserList();

    User getUserByUsername(@Param("username") String username);

    User getUserById(@Param("id") Long id);

    List<User> getFriendList(@Param("id") Long id);

    int insertFriend(
            @Param("userId") Long userId,
            @Param("friendId") Long friendId,
            @Param("hideMyMM") int hideMyMM,
            @Param("hideHisMM") int hideHisMM,
            @Param("star") int star,
            @Param("blacklist") int blacklist,
            @Param("chatroom") int chatroom);

    int updateRemark(@Param("userId") Long userId, @Param("friendId") Long friendId,
                     @Param("remark") String remark);

    int updateMomentSetting(@Param("userId") Long userId, @Param("friendId") Long friendId,
                            @Param("hideMyMM") Integer hideMyMM, @Param("hideHisMM") Integer hideHisMM);

    int updateStar(@Param("userId") Long userId, @Param("friendId") Long friendId,
                   @Param("star") int star);

    int insert(@Param("user") User user);

    int insertList(@Param("users") List<User> users);

    int update(@Param("user") User user);

    int delete(@Param("id") Long id);
}
