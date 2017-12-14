package com.lan.ichat.im.handler;

import com.lan.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spoom.im.sdk.server.IMConstant;
import org.spoom.im.sdk.server.IMSession;
import org.spoom.im.sdk.server.MessageHandler;
import org.spoom.im.sdk.server.SessionManager;
import org.spoom.im.sdk.server.model.CallMessage;
import org.spoom.im.sdk.server.model.ChatMessage;
import org.spoom.im.sdk.server.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * package com.lan.ichat.im.handler
 *
 * @author lanzongxiao
 * @date 2017/11/3
 */
@Component
public class BindHandler implements MessageHandler {

    protected final Logger logger = LoggerFactory.getLogger(BindHandler.class);

    @Autowired
    private SessionManager sessionManager;

    @Override
    public Reply process(IMSession newSession, CallMessage callMessage) {
        Reply reply = new Reply();
        reply.setAction(callMessage.getAction());
        reply.setCode(IMConstant.ReturnCode.CODE_200);
        try {
            String account = callMessage.get("account");
            newSession.setAccount(account);
            newSession.setDeviceId(callMessage.get("deviceId"));
            newSession.setHost(InetAddress.getLocalHost().getHostAddress());
            newSession.setDeviceType(callMessage.get("deviceType"));
            newSession.setDeviceModel(callMessage.get("deviceModel"));
            newSession.setClientVersion(callMessage.get("clientVersion"));
            newSession.setSystemVersion(callMessage.get("systemVersion"));
            newSession.setBindTime(System.currentTimeMillis());
            newSession.setPackageName(callMessage.get("packageName"));

            IMSession oldSession = sessionManager.get(account);
            // 同一账号，不同设备上登录时，让原设备上的账号下线
            if (newSession.fromOtherDevice(oldSession)) {
                sendForceOfflineMessage(oldSession, account, newSession.getDeviceModel());
            }
            if (newSession.equals(oldSession)) {
                oldSession.setStatus(true);
                sessionManager.update(oldSession);
                reply.put("id", oldSession.getId());
                return reply;
            }
            newSession.setBindTime(System.currentTimeMillis());
            newSession.setStatus(true);
            sessionManager.add(newSession);
            logger.info("bind successful account:" + account + " nid:" + newSession.getId());
        } catch (Exception e) {
            newSession.setStatus(false);
            logger.error("bind failed account:" + callMessage.get("account") + " id:" + newSession.getId(), e);
        }
        reply.put("id", newSession.getId());
        return reply;
    }

    private void sendForceOfflineMessage(IMSession oldSession, String account, String deviceModel) {
        ChatMessage msg = new ChatMessage();
        msg.setMsgId(StringUtils.getUUID());
        msg.setChatType(IMConstant.MessageAction.ACTION_FORCEOFFLINE);
        msg.setTo(account);
        msg.setFrom("system");
        msg.setBody(deviceModel);
        if (oldSession.isConnected()) {
            oldSession.write(msg);
        }
    }
}
