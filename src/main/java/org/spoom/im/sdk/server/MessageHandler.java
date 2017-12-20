package org.spoom.im.sdk.server;

import org.spoom.im.sdk.server.model.CmdMessage;

/**
 * package org.spoom.im.sdk.server
 *
 * @author lanzongxiao
 * @date 08/12/2017
 */
public interface MessageHandler {

    CmdMessage process(IMSession session, CmdMessage callMessage);
}
