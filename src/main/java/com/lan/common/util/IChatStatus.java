package com.lan.common.util;

/**
 * package com.lan.common.util
 *
 * @author lanzongxiao
 * @date 2017/11/1
 */
public enum IChatStatus {
    // series 1: information
    CONTINUE(100, "Continue"),

    // series 2: success
    SUCCESS(200, "Success"),
    LOGIN_SUCCESS(201, "Login success"),
    LOGOUT_SUCCESS(202, "Logout success"),
    GET_SUCCESS(210, "Get success"),
    INSERT_SUCCESS(220, "Insert success"),
    UPDATE_SUCCESS(230, "Update success"),

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
    UPDATE_FAILURE(430, "Update failure");


    private final int value;
    private final String reasonPhrase;

    private IChatStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int value() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public String toString() {
        return "Code: " + this.value + ", reason: " + this.reasonPhrase;
    }

    public static IChatStatus valueOf(int statusCode) {
        IChatStatus[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            IChatStatus status = var1[var3];
            if (status.value == statusCode) {
                return status;
            }
        }

        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }

    public static enum Series {
        INFORMATIONAL(1),
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
            return valueOf(status.value);
        }
    }
}