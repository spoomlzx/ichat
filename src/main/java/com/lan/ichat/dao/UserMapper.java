package com.lan.ichat.dao;

import com.lan.ichat.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserEntity> getUserList();

    UserEntity getUserByUsername(@Param("username") String username);

    UserEntity getUserById(@Param("id") Long id);

    List<UserEntity> getFriendList(@Param("id") Long id);

    int addFriend(
            @Param("userId") Long userId,
            @Param("friendId") Long friendId,
            @Param("hideMyMM") int hideMyMM,
            @Param("hideHisMM") int hideHisMM,
            @Param("star") int star,
            @Param("blacklist") int blacklist,
            @Param("chatroom") int chatroom);

    int insert(@Param("userEntity") UserEntity userEntity);

    int insertList(@Param("userEntitys") List<UserEntity> userEntitys);

    int update(@Param("userEntity") UserEntity userEntity);

    int delete(@Param("id") Long id);
}
