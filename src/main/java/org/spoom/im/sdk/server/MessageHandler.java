package org.spoom.im.sdk.server;

import org.spoom.im.sdk.server.model.CallMessage;
import org.spoom.im.sdk.server.model.Reply;

/**
 * package org.spoom.im.sdk.server
 *
 * @author lanzongxiao
 * @date 08/12/2017
 */
public interface MessageHandler {

    Reply process(IMSession session, CallMessage callMessage);
}
