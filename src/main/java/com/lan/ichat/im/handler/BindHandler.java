package com.lan.ichat.im.handler;

import com.lan.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spoom.im.sdk.server.IMConstant;
import org.spoom.im.sdk.server.IMSession;
import org.spoom.im.sdk.server.MessageHandler;
import org.spoom.im.sdk.server.SessionManager;
import org.spoom.im.sdk.server.model.CmdMessage;
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
    public CmdMessage process(IMSession newSession, CmdMessage callMessage) {
        CmdMessage replyMessage = new CmdMessage();
        replyMessage.setMsgId(StringUtils.getUUID());
        replyMessage.setAction(callMessage.getAction());
        replyMessage.setTime(System.currentTimeMillis());
        try {
            String account = callMessage.get("account");
            replyMessage.setTo(account);
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
                replyMessage.put("id", oldSession.getId());
                return replyMessage;
            }
            newSession.setBindTime(System.currentTimeMillis());
            newSession.setStatus(true);
            sessionManager.add(newSession);
        } catch (Exception e) {
            newSession.setStatus(false);
            logger.error("bind failed account:" + callMessage.get("account") + " id:" + newSession.getId(), e);
        }
        replyMessage.put("id", newSession.getId());
        return replyMessage;
    }

    private void sendForceOfflineMessage(IMSession oldSession, String account, String deviceModel) {
        CmdMessage cmdMessage = new CmdMessage();
        cmdMessage.setMsgId(StringUtils.getUUID());
        cmdMessage.setAction(IMConstant.MessageAction.ACTION_FORCE_OFFLINE);
        cmdMessage.setFrom("system");
        cmdMessage.setTo(account);
        cmdMessage.setTime(System.currentTimeMillis());
        cmdMessage.put("deviceModel", deviceModel);
        if (oldSession.isConnected()) {
            oldSession.write(cmdMessage);
        }
    }
}
