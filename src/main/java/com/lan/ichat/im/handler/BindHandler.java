package com.lan.ichat.im.handler;

import com.farsunset.cim.sdk.server.constant.CIMConstant;
import com.farsunset.cim.sdk.server.handler.CIMRequestHandler;
import com.farsunset.cim.sdk.server.model.Message;
import com.farsunset.cim.sdk.server.model.ReplyBody;
import com.farsunset.cim.sdk.server.model.SentBody;
import com.farsunset.cim.sdk.server.session.CIMSession;
import com.lan.common.util.StringUtils;
import com.lan.ichat.service.IChatSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BindHandler implements CIMRequestHandler {

    protected final Logger logger = LoggerFactory.getLogger(BindHandler.class);

    @Autowired
    private IChatSessionManager iChatSessionManager;

    @Override
    public ReplyBody process(CIMSession newSession, SentBody message) {
        ReplyBody reply = new ReplyBody();
        reply.setCode(CIMConstant.ReturnCode.CODE_200);
        try {
            String account = message.get("account");
            newSession.setGid(StringUtils.getUUID());
            newSession.setAccount(account);
            newSession.setDeviceId(message.get("deviceId"));
            newSession.setHost(InetAddress.getLocalHost().getHostAddress());
            newSession.setChannel(message.get("channel"));
            newSession.setDeviceModel(message.get("device"));
            newSession.setClientVersion(message.get("version"));
            newSession.setSystemVersion(message.get("osVersion"));
            newSession.setBindTime(System.currentTimeMillis());
            newSession.setPackageName(message.get("packageName"));

            CIMSession oldSession = this.iChatSessionManager.get(account);
            // 同一账号，不同设备上登录时，让原设备上的账号下线
            if (newSession.fromOtherDevice(oldSession)) {
                sendForceOfflineMessage(oldSession, account, newSession.getDeviceModel());
            }
            if (newSession.equals(oldSession)) {
                oldSession.setStatus(CIMSession.STATUS_ENABLED);
                this.iChatSessionManager.update(oldSession);
                reply.put("sid", oldSession.getGid());
                return reply;
            }
            closeQuietly(oldSession);
            newSession.setBindTime(System.currentTimeMillis());
            newSession.setHeartbeat(System.currentTimeMillis());
            logger.info("bind successful account:" + account + " nid:" + newSession.getNid());
        } catch (Exception e) {
            newSession.setStatus(CIMSession.STATUS_ENABLED);
            logger.error("bind failed account:" + message.get("account") + " nid:" + newSession.getNid(), e);
        }
        reply.put("sid", newSession.getGid());
        return reply;
    }

    private void sendForceOfflineMessage(CIMSession oldSession, String account, String deviceModel) {
        Message msg = new Message();
        msg.setMid(StringUtils.getUUID());
        msg.setAction(CIMConstant.MessageAction.ACTION_999);
        msg.setReceiver(account);
        msg.setSender("system");
        msg.setContent(deviceModel);
        closeQuietly(oldSession, msg);
    }

    //同一设备切换网络时关闭旧的连接
    private void closeQuietly(CIMSession oldSession) {
        if (oldSession != null && oldSession.isConnected()) {
            oldSession.removeAttribute(CIMConstant.SESSION_KEY);
            oldSession.closeNow();
        }
    }

    private void closeQuietly(CIMSession oldSession, Message msg) {
        if (oldSession.isConnected()) {
            oldSession.write(msg);
            oldSession.removeAttribute(CIMConstant.SESSION_KEY);
            oldSession.closeNow();
        }
    }
}
