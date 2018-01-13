package com.lan.common.util;

/**
 * package com.lan.common.util
 *
 * @author lanzongxiao
 * @date 2017/11/1
 */
public enum IChatStatus {
    // series 1: message
    // 10* system message
    SYSTEM_BROADCAST_MESSAGE(100, "System broadcast message"), // 系统广播消息，由后台进行发送
    SYSTEM_CUSTOMIZE_MESSAGE(101, "System customize message"), // 系统定制消息，消息被阅读
    // 11* user message
    USER_DEFAULT_MESSAGE(110, "Default message between users"), // 用户普通消息
    USER_FORCE_OFFLINE_MESSAGE(111, "User force offline message"), // 强制用户下线的消息
    USER_AVATAR_CHANGED_MESSAGE(112, "User avatar changed message"), // 用户更换头像消息
    USER_INFO_CHANGED_MESSAGE(113, "User information changed message"), // 用户信息更新消息
    // 12*，13* group message
    GROUP_USER_MESSAGE(120, "Group message send by user"), // 用户发送的群消息，是由发送人->ichat-server
    GROUP_BROADCAST_MESSAGE(121, "Group broadcast message"), // 群里的消息广播给其他群用户
    GROUP_APPLY_MESSAGE(123, "Group apply message"), // 申请加群消息
    GROUP_APPLY_AGREE_MESSAGE(124, "Group apply agree message"), // 同意申请入群消息
    GROUP_APPLY_DENY_MESSAGE(125, "Group apply deny message"), // 拒绝申请入群消息
    GROUP_DISBAND_MESSAGE(126, "Group disband message"), // 群组解散消息
    GROUP_INVITE_MESSAGE(127, "Group invite message"), // 被邀请入群消息
    GROUP_INVITE_AGREE_MESSAGE(128, "Group invite agree message"), // 同意被邀请加群消息
    GROUP_ELIMINATE_MESSAGE(129, "Group eliminate message"), // 被踢出群消息
    GROUP_USER_QUIT_MESSAGE(130, "Group user quit message"), // 用户退群消息
    GROUP_USER_ENTER_MESSAGE(131, "Group user enter message"), // 用户加入了群
    GROUP_INFO_CHANGED_MESSAGE(312, "Group information changed message"), // 群信息被修改消息
    // 14* public message
    PUBLIC_DEFAULT_MESSAGE(140, "Public default message"), // 公众号向用户发的消息
    PUBLIC_INFO_CHANGED_MESSAGE(141, "Public information changed message"), // 公众号信息更新
    PUBLIC_MENU_CHANGED_MESSAGE(142, "Public menu changed message"), // 公众号菜单信息更新
    // 15* moment message
    MOMENT_PUBLISH_MESSAGE(150, "Moment published by friend"), // 好友发布新的动态
    MOMENT_COMMENT_MESSAGE(151, "Moment comment message"), // 动态被评论
    MOMENT_COMMENT_REPLY_MESSAGE(152, "Moment comment reply message"), // 动态评论回复
    MOMENT_DELETE_MESSAGE(153, "Moment deleted message"), // 动态被删除消息
    //漂流瓶的消息另外定义

    // series 2: success
    SUCCESS(200, "Success"),

    // series 3: exception
    TOKEN_INVALID(300, "Token is invalid"),
    OVERSTEP_AUTHORITY(302, "verstep authority"),
    TOKEN_EMPTY(301, "Token cannot be empty"),

    USER_NOT_EXIST(311, "User is not exist"),
    CREDENTIAL_INVALID(312, "Credential is invalid"),
    SQL_EXCEPTION(350, "Sql exception"),


    // series 4: error
    FAILURE(400, "Failure"),
    TOKEN_DEL_FAILURE(403, "Delete token failure"),

    GET_FAILURE(410, "Get failure"),
    INSERT_FAILURE(420, "Insert failure"),
    UPDATE_FAILURE(430, "Update failure"),
    DELETE_FAILURE(440, "Delete failure");


    private final int code;
    private final String info;

    private IChatStatus(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int code() {
        return this.code;
    }

    public String getInfo() {
        return this.info;
    }

    public String toString() {
        return "Code: " + this.code + ", info: " + this.info;
    }

    public static IChatStatus valueOf(int statusCode) {
        IChatStatus[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            IChatStatus status = var1[var3];
            if (status.code == statusCode) {
                return status;
            }
        }

        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }

    public static enum Series {
        MESSAGE(1),
        SUCCESSFUL(2),
        REDIRECTION(3),
        CLIENT_ERROR(4);

        private final int value;

        private Series(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }

        public static IChatStatus.Series valueOf(int status) {
            int seriesCode = status / 100;
            IChatStatus.Series[] var2 = values();
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                IChatStatus.Series series = var2[var4];
                if (series.value == seriesCode) {
                    return series;
                }
            }

            throw new IllegalArgumentException("No matching constant for [" + status + "]");
        }

        public static IChatStatus.Series valueOf(IChatStatus status) {
            return valueOf(status.code);
        }
    }
}