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
    public CmdMessage process(IMSession newSession, CmdMessage cmdMessage) {
        CmdMessage replyMessage = new CmdMessage();
        replyMessage.setMsgId(StringUtils.getUUID());
        replyMessage.setAction(cmdMessage.getAction());
        replyMessage.setTime(System.currentTimeMillis());
        replyMessage.setMsgFrom("system");
        try {
            String account = cmdMessage.get("account");
            replyMessage.setMsgTo(account);
            newSession.setAccount(account);
            newSession.setDeviceId(cmdMessage.get("deviceId"));
            newSession.setHost(InetAddress.getLocalHost().getHostAddress());
            newSession.setDeviceType(cmdMessage.get("deviceType"));
            newSession.setDeviceModel(cmdMessage.get("deviceModel"));
            newSession.setClientVersion(cmdMessage.get("clientVersion"));
            newSession.setSystemVersion(cmdMessage.get("systemVersion"));
            newSession.setBindTime(System.currentTimeMillis());
            newSession.setPackageName(cmdMessage.get("packageName"));

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
            replyMessage.put("id", newSession.getId());
        } catch (Exception e) {
            newSession.setStatus(false);
            logger.error("bind failed account:" + cmdMessage.get("account") + " id:" + newSession.getId(), e);
        }
        return replyMessage;
    }

    private void sendForceOfflineMessage(IMSession oldSession, String account, String deviceModel) {
        CmdMessage cmdMessage = new CmdMessage();
        cmdMessage.setMsgId(StringUtils.getUUID());
        cmdMessage.setAction(IMConstant.MessageAction.ACTION_FORCE_OFFLINE);
        cmdMessage.setMsgFrom("system");
        cmdMessage.setMsgTo(account);
        cmdMessage.setTime(System.currentTimeMillis());
        cmdMessage.put("deviceModel", deviceModel);
        if (oldSession.isConnected()) {
            oldSession.write(cmdMessage);
        }
    }
}
