/**
 * File: WeChatGlobalConfig
 * Author: DorSey Q F TANG
 * Created: 2020/2/9 10:43
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.model;

import java.util.Date;

/**
 * The container, which contains the global configuration for <code>WeChat</code>
 */
public class WeChatGlobalConfig {

    private String wechatAccount;
    private String appId;
    private String appSecret;
    private String encodingAESKey;
    private String token;
    private Date createTime;

    /**
     * Empty constructor of {@link WeChatGlobalConfig}.
     */
    public WeChatGlobalConfig() {
        // empty
    }

    public String getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(final String wechatAccount) {
        this.wechatAccount = wechatAccount;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeChatGlobalConfig that = (WeChatGlobalConfig) o;

        if (wechatAccount != null ? !wechatAccount.equals(that.wechatAccount) : that.wechatAccount != null)
            return false;
        if (appId != null ? !appId.equals(that.appId) : that.appId != null) return false;
        if (appSecret != null ? !appSecret.equals(that.appSecret) : that.appSecret != null) return false;
        if (encodingAESKey != null ? !encodingAESKey.equals(that.encodingAESKey) : that.encodingAESKey != null)
            return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        return createTime != null ? createTime.equals(that.createTime) : that.createTime == null;
    }

    @Override
    public int hashCode() {
        int result = (wechatAccount != null) ? wechatAccount.hashCode() : 0;
        result = 31 * result + (appId != null ? appId.hashCode() : 0);
        result = 31 * result + (appSecret != null ? appSecret.hashCode() : 0);
        result = 31 * result + (encodingAESKey != null ? encodingAESKey.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeChatGlobalConfig{" +
                "wechatAccount=" + wechatAccount +
                ", appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                ", encodingAESKey='" + encodingAESKey + '\'' +
                ", token='" + token + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

