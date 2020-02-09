/**
 * File: ZeroWebConfig
 * Author: DorSey Q F TANG
 * Created: 2020/2/5 11:00
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "zero.web")
public class ZeroWebConfig {
    private String token;
    private String appId;
    private String aesKey;
    private String appSecret;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
