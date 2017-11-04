package com.lan.common.util;

import java.util.UUID;

/**
 * package com.lan.common.util
 *
 * @author lanzongxiao
 * @date 2017/11/3
 */
public class StringUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean isEmpty(Object obj) {
        if (null == obj)
            return true;
        if ("".equals(obj.toString().trim())) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Object obj) {

        return !isEmpty(obj);
    }
}
