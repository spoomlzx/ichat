package org.spoom.im.sdk.server.coder;

import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.log4j.Logger;
import org.spoom.im.sdk.server.IMConstant;
import org.spoom.im.sdk.server.model.CallMessage;
import org.spoom.im.sdk.server.model.HeartbeatRequest;
import org.spoom.im.sdk.server.model.Message;
import org.spoom.im.sdk.server.model.proto.CallMessageProto;
import org.spoom.im.sdk.server.model.proto.MessageProto;

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
                HeartbeatRequest heartbeatRequest = HeartbeatRequest.getInstance();
                logger.info("decode: " + heartbeatRequest.toString());
                return heartbeatRequest;
            case IMConstant.ProtobufType.CALL_MESSAGE:
                CallMessageProto.CallMessage callMsg = CallMessageProto.CallMessage.parseFrom(bytes);
                CallMessage callMessage = new CallMessage();
                callMessage.setCallType(callMsg.getCallType());
                callMessage.putAll(callMsg.getDataMap());
                callMessage.setTime(callMsg.getTime());
                logger.info("decode: " + callMessage.toString());
                return callMessage;
            case IMConstant.ProtobufType.MESSAGE:
                MessageProto.Message messageProto = MessageProto.Message.parseFrom(bytes);
                Message message = new Message();
                message.setMsgId(messageProto.getMsgId());
                message.setChatType(messageProto.getChatType());
                message.setFormat(messageProto.getFormat());
                message.setFrom(messageProto.getFrom());
                message.setTo(messageProto.getTo());
                message.setBody(messageProto.getBody());
                message.setExtra(messageProto.getExtra());
                message.setTime(messageProto.getTime());
                logger.info("decode: " + message.toString());
                return message;
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
