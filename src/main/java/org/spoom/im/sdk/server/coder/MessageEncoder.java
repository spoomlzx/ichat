package org.spoom.im.sdk.server.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.log4j.Logger;
import org.spoom.im.sdk.server.IMConstant;
import org.spoom.im.sdk.server.model.Protobufable;

/**
 * package org.spoom.im.sdk.server.coder
 *
 * @author lanzongxiao
 * @date 08/12/2017
 */
public class MessageEncoder extends MessageToByteEncoder<Object> {
    protected final Logger logger = Logger.getLogger(MessageEncoder.class.getSimpleName());

    @Override
    protected void encode(ChannelHandlerContext context, Object obj, ByteBuf buf) throws Exception {
        if (obj instanceof Protobufable) {
            logger.info(obj.toString());
            Protobufable data = (Protobufable) obj;
            byte[] byteArray = data.getByteArray();
            buf.writeBytes(createHeader(data.getType(), byteArray.length));
            buf.writeBytes(byteArray);
        }
    }

    /**
     * 消息体最大为65535
     *
     * @param type
     * @param length
     * @return
     */
    private byte[] createHeader(byte type, int length) {
        byte[] header = new byte[IMConstant.HEADER_LENGTH];
        header[0] = type;
        header[1] = (byte) (length & 0xff);
        header[2] = (byte) ((length >> 8) & 0xff);
        return header;
    }
}
