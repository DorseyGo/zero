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
    private String wechatAccount;
    private boolean debug;

    public String getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(final String wechatAccount) {
        this.wechatAccount = wechatAccount;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
