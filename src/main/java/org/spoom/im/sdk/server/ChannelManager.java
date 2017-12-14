package org.spoom.im.sdk.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spoom.im.sdk.server.coder.MessageDecoder;
import org.spoom.im.sdk.server.coder.MessageEncoder;
import org.spoom.im.sdk.server.model.CallMessage;
import org.spoom.im.sdk.server.model.HeartbeatRequest;
import org.spoom.im.sdk.server.model.ChatMessage;
import org.spoom.im.sdk.server.model.Reply;

import java.io.IOException;
import java.util.HashMap;

/**
 * package org.spoom.im.sdk.server
 *
 * @author lanzongxiao
 * @date 08/12/2017
 */
@ChannelHandler.Sharable
public class ChannelManager extends SimpleChannelInboundHandler<Object> {
    private final static Logger logger = LoggerFactory.getLogger(ChannelManager.class);

    private HashMap<Integer, MessageHandler> handlers = new HashMap<>();
    private int port;
    private SessionManager sessionManager;

    //连接空闲时间
    public static final int READ_IDLE_TIME = 40;//秒
    //连接空闲时间
    public static final int WRITE_IDLE_TIME = 20;//秒
    public static final int PING_TIME_OUT = 30;//心跳响应 超时为30秒

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
            setLastHeartbeatTime(context);
        }
        if (obj instanceof CallMessage) {
            CallMessage message = (CallMessage) obj;
            MessageHandler handler = handlers.get(message.getAction());
            if (handler == null) {
                Reply reply = new Reply();
                reply.setAction(message.getAction());
                reply.setCode(IMConstant.ReturnCode.CODE_404);
                reply.setMessage("No handler");
                session.write(reply);
            } else {
                Reply reply = handler.process(session, message);
                if (reply != null) {
                    session.write(reply);
                }
            }
        }
        if (obj instanceof ChatMessage) {
            ChatMessage chatMessage = (ChatMessage) obj;
            session = sessionManager.get(chatMessage.getTo());
            if (session != null) {
                chatMessage.setFrom(session.getAccount());
                session.write(chatMessage);
                logger.info("chat message to transmit： " + chatMessage.toString());
            }
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) throws Exception {
        IMSession imSession = new IMSession(context.channel());
        logger.warn("sessionClosed()... from " + context.channel().remoteAddress() + " id:" + imSession.getId() + ",isConnected:" + context.channel().isActive());
        MessageHandler handler = handlers.get(IMConstant.HandlerType.CLOSE_SESSION);
        if (handler != null) {
            handler.process(imSession, null);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext context, Object evt) throws Exception {
        // 超过time out没有收到心跳，则断开channel
        if (evt instanceof IdleStateEvent && ((IdleStateEvent) evt).state().equals(IdleState.READER_IDLE)) {
            logger.warn(IdleState.READER_IDLE + "... from " + context.channel().remoteAddress() + " id:" + context.channel().id().asShortText());
            long lastTime = getLastHeartbeatTime(context);
            if (System.currentTimeMillis() - lastTime >= PING_TIME_OUT) {
                context.channel().close();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) throws Exception {
        logger.error("exceptionCaught()... from " + context.channel().remoteAddress() + " id:" + context.channel().id().asShortText(), cause);
        context.channel().close();
    }

    private void setLastHeartbeatTime(ChannelHandlerContext context) {
        context.channel().attr(AttributeKey.valueOf(IMConstant.KEY_HEARTBEAT)).set(System.currentTimeMillis());
    }

    private long getLastHeartbeatTime(ChannelHandlerContext context) {
        return (long) context.channel().attr(AttributeKey.valueOf(IMConstant.KEY_HEARTBEAT)).get();
    }

    public void setPort(int port) {
        this.port = port;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setHandlers(HashMap<Integer, MessageHandler> handlers) {
        this.handlers = handlers;
    }
}
