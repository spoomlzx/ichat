package com.lan.ichat.service;

/**
 * package com.lan.ichat.service
 *
 * @author lanzongxiao
 * @date 2017/11/2
 */
public interface BaseService<T> {

    void set(String str, T t);

    T get(String str);

    void delete(String str);
}
