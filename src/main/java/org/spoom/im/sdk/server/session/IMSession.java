package org.spoom.im.sdk.server.session;

import io.netty.channel.Channel;

import java.io.Serializable;

/**
 * package org.spoom.im.sdk.server.session
 *
 * @author lanzongxiao
 * @date 08/12/2017
 */
public class IMSession implements Serializable {
    private static final long serialVersionUID = -5169024933755423784L;
    private transient Channel channel;

    private String id;//session在本台服务器上的ID
    private String deviceId;//客户端ID  (设备号码+应用包名)
    private String host;//session绑定的服务器IP
    private String account;//session绑定的账号
    private String deviceType;//终端设备类型
    private String deviceModel;//终端设备型号
    private String clientVersion;//终端应用版本
    private String systemVersion;//终端系统版本
    private String packageName;//终端应用包名
    private Long bindTime;//登录时间
    private int status;// 状态

    public IMSession(Channel channel) {
        this.channel = channel;
        this.id = channel.id().asShortText();
    }

    public IMSession() {

    }

    public boolean write(Object msg) {
        return channel != null && channel.writeAndFlush(msg).awaitUninterruptibly(5000);
    }

    public boolean fromOtherDevice(Object o) {
        if (o instanceof IMSession) {
            IMSession t = (IMSession) o;
            if (t.deviceId != null && deviceId != null) {
                return !t.deviceId.equals(deviceId);
            }
        }
        return false;
    }

    public boolean isConnected() {
        return channel != null && channel.isActive();
    }

    public void closeNow() {
        if (channel != null)
            channel.close();
    }

    public int hashCode() {
        return (deviceId + id + host).hashCode();
    }

    public boolean equals(Object o) {
        if (o instanceof IMSession) {
            return hashCode() == o.hashCode();
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Long getBindTime() {
        return bindTime;
    }

    public void setBindTime(Long bindTime) {
        this.bindTime = bindTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}