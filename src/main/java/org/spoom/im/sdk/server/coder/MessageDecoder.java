package org.spoom.im.sdk.server.coder;

import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.log4j.Logger;
import org.spoom.im.sdk.server.IMConstant;
import org.spoom.im.sdk.server.model.ChatMessage;
import org.spoom.im.sdk.server.model.CmdMessage;
import org.spoom.im.sdk.server.model.HeartbeatRequest;
import org.spoom.im.sdk.server.model.proto.ChatMessageProto;
import org.spoom.im.sdk.server.model.proto.CmdMessageProto;

import java.util.List;

/**
 * package org.spoom.im.sdk.server.coder
 *
 * @author lanzongxiao
 * @date 08/12/2017
 */
public class MessageDecoder extends ByteToMessageDecoder {
    protected final Logger logger = Logger.getLogger(MessageDecoder.class);

    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf buf, List<Object> list) throws Exception {
        if (buf.readableBytes() < IMConstant.HEADER_LENGTH) {
            return;
        }
        buf.markReaderIndex();
        byte contentType = buf.readByte();
        byte lowByte = buf.readByte();
        byte highByte = buf.readByte();
        int contentLength = getContentLength(lowByte, highByte);
        // 如果消息体没有接收完整，则重置读取，等待下一次重新读取
        if (contentLength > buf.readableBytes()) {
            buf.resetReaderIndex();
            return;
        }
        byte[] dataBytes = new byte[contentLength];
        buf.readBytes(dataBytes);
        Object message = parseMessageObj(dataBytes, contentType);
        if (message != null) {
            list.add(message);
        }
    }

    private Object parseMessageObj(byte[] bytes, byte type) throws InvalidProtocolBufferException {
        switch (type) {
            case IMConstant.ProtobufType.HB_REQUEST:
                return HeartbeatRequest.getInstance();
            case IMConstant.ProtobufType.CMD_MESSAGE:
                CmdMessageProto.CmdMessage cmdMsg = CmdMessageProto.CmdMessage.parseFrom(bytes);
                CmdMessage cmdMessage = new CmdMessage();
                cmdMessage.setMsgId(cmdMsg.getMsgId());
                cmdMessage.setAction(cmdMsg.getAction());
                cmdMessage.setChatType(cmdMsg.getChatType());
                cmdMessage.setMsgFrom(cmdMsg.getMsgFrom());
                cmdMessage.setMsgTo(cmdMsg.getMsgTo());
                cmdMessage.setTime(cmdMsg.getTime());
                cmdMessage.putAll(cmdMsg.getDataMap());
                logger.info("decode: " + cmdMessage.toString());
                return cmdMessage;
            case IMConstant.ProtobufType.CHAT_MESSAGE:
                ChatMessageProto.ChatMessage messageProto = ChatMessageProto.ChatMessage.parseFrom(bytes);
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setMsgId(messageProto.getMsgId());
                chatMessage.setChatType(messageProto.getChatType());
                chatMessage.setMsgType(messageProto.getMsgType());
                chatMessage.setMsgFrom(messageProto.getMsgFrom());
                chatMessage.setMsgTo(messageProto.getMsgTo());
                chatMessage.setBody(messageProto.getBody());
                chatMessage.setExtra(messageProto.getExtra());
                chatMessage.setTime(messageProto.getTime());
                logger.info("decode: " + chatMessage.toString());
                return chatMessage;
            default:
                return null;
        }
    }

    /**
     * 从2个byte中读出message 的 length
     *
     * @param lowByte  低位byte
     * @param highByte 高位byte
     * @return length of message
     */
    private int getContentLength(byte lowByte, byte highByte) {
        int l = (lowByte & 0xff);
        int h = (highByte & 0xff);
        return (l | (h <<= 8));
    }
}
