package org.spoom.im.sdk.server.session;

import java.util.List;

/**
 * package org.spoom.im.sdk.server.session
 *
 * @author lanzongxiao
 * @date 08/12/2017
 */
public interface SessionManager {
    /**
     * 添加新的session
     */
    public void add(IMSession session);

    /**
     * 更新session
     */
    public void update(IMSession session);

    /**
     * @param account 客户端session的 key 一般可用 用户账号来对应session
     * @return
     */
    IMSession get(String account);

    /**
     * 获取所有session
     *
     * @return
     */
    public List<IMSession> queryAll();


    /**
     * 删除session
     *
     * @param account
     */
    public void remove(String account);
}
