package org.spoom.im.sdk.server;

import org.spoom.im.sdk.server.model.CmdMessage;
import org.spoom.im.sdk.server.model.ChatMessage;

/**
 * package org.spoom.im.sdk.server
 *
 * @author spoomlan
 * @date 18/12/2017
 */
public interface IDispatcher {

    void dispatchCallMessage(IMSession session,CmdMessage callMessage);

    void dispatchChatMessage(IMSession session,ChatMessage chatMessage);

}
