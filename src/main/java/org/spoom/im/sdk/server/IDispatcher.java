package org.spoom.im.sdk.server;

import org.spoom.im.sdk.server.model.CallMessage;
import org.spoom.im.sdk.server.model.ChatMessage;

/**
 * package org.spoom.im.sdk.server
 *
 * @author spoomlan
 * @date 18/12/2017
 */
public interface IDispatcher {

    void dispatchCallMessage(IMSession session,CallMessage callMessage);

    void dispatchChatMessage(IMSession session,ChatMessage chatMessage);

}
