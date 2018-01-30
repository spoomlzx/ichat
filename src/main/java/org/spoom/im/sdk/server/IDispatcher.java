package org.spoom.im.sdk.server;

import io.netty.channel.Channel;
import org.spoom.im.sdk.server.model.CmdMessage;
import org.spoom.im.sdk.server.model.ChatMessage;

/**
 * package org.spoom.im.sdk.server
 *
 * @author spoomlan
 * @date 18/12/2017
 */
public interface IDispatcher {

    void dispatchCmdMessage(Channel channel, CmdMessage callMessage);

    void dispatchChatMessage(Channel channel,ChatMessage chatMessage);

}
