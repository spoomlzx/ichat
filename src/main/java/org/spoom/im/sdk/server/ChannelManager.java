package org.spoom.im.sdk.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spoom.im.sdk.server.coder.MessageDecoder;
import org.spoom.im.sdk.server.coder.MessageEncoder;
import org.spoom.im.sdk.server.model.CmdMessage;
import org.spoom.im.sdk.server.model.ChatMessage;
import org.spoom.im.sdk.server.model.HeartbeatRequest;
import org.spoom.im.sdk.server.model.HeartbeatResponse;

import java.io.IOException;

/**
 * package org.spoom.im.sdk.server
 *
 * @author lanzongxiao
 * @date 08/12/2017
 */
@ChannelHandler.Sharable
public class ChannelManager extends SimpleChannelInboundHandler<Object> {
    private final static Logger logger = LoggerFactory.getLogger(ChannelManager.class);

    private int port;
    private IDispatcher dispatcher;

    //连接空闲时间
    public static final int READ_IDLE_TIME = 240;//秒
    //连接空闲时间
    public static final int WRITE_IDLE_TIME = 20;//秒

    public void bind() throws IOException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup());
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);

        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new MessageDecoder());
                ch.pipeline().addLast(new MessageEncoder());
                ch.pipeline().addLast(new IdleStateHandler(READ_IDLE_TIME, WRITE_IDLE_TIME, 0));
                ch.pipeline().addLast(ChannelManager.this);
            }
        });
        bootstrap.bind(port);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext context) throws Exception {
        logger.info("channelCreated()... from " + context.channel().remoteAddress() + " id:" + context.channel().id().asShortText());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, Object obj) throws Exception {
        IMSession session = new IMSession(context.channel());
        if (obj instanceof HeartbeatRequest) {
            context.channel().writeAndFlush(HeartbeatResponse.getInstance());
        }
        if (obj instanceof CmdMessage) {
            CmdMessage message = (CmdMessage) obj;
            dispatcher.dispatchCallMessage(session, message);
        }
        if (obj instanceof ChatMessage) {
            ChatMessage chatMessage = (ChatMessage) obj;
            dispatcher.dispatchChatMessage(session, chatMessage);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) throws Exception {
        IMSession session = new IMSession(context.channel());
        logger.warn("sessionClosed()... from " + context.channel().remoteAddress() + " id:" + session.getId() + ",isConnected:" + context.channel().isActive());
        CmdMessage cmdMessage = new CmdMessage();
        cmdMessage.setAction(IMConstant.MessageAction.ACTION_LOGOUT);
        dispatcher.dispatchCallMessage(session, cmdMessage);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext context, Object evt) throws Exception {
        // 超过time out没有收到心跳，则断开channel
        if (evt instanceof IdleStateEvent && ((IdleStateEvent) evt).state().equals(IdleState.READER_IDLE)) {
            logger.warn(IdleState.READER_IDLE + "... from " + context.channel().remoteAddress() + " id:" + context.channel().id().asShortText());
            context.channel().close();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) throws Exception {
        logger.error("exceptionCaught()... from " + context.channel().remoteAddress() + " id:" + context.channel().id().asShortText(), cause);
        context.channel().close();
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setDispatcher(IDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
}
