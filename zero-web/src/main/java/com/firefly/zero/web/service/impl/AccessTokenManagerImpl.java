/**
 * File: AccessTokenManagerImpl
 * Author: DorSey Q F TANG
 * Created: 2020/2/5 15:20
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service.impl;

import com.firefly.zero.web.config.ZeroWebConfig;
import com.firefly.zero.web.model.WeChatGlobalConfig;
import com.firefly.zero.web.service.AccessTokenManager;
import com.firefly.zero.web.service.WeChatGlobalConfigService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("accessTokenManager")
public class AccessTokenManagerImpl implements AccessTokenManager {

    private static final Logger logger = LoggerFactory.getLogger(AccessTokenManagerImpl.class);

    private final WeChatGlobalConfigService weChatGlobalConfigService;
    private final ZeroWebConfig zeroWebConfig;

    @Autowired
    public AccessTokenManagerImpl(
            @Qualifier("wechatGlobalConfigService") final WeChatGlobalConfigService weChatGlobalConfigService,
            final ZeroWebConfig config) {
        this.weChatGlobalConfigService = weChatGlobalConfigService;
        this.zeroWebConfig = config;
    }

    @Override
    public String getAccessToken() {
        final WxMpInMemoryConfigStorage provider = new WxMpInMemoryConfigStorage();
        final WeChatGlobalConfig config = weChatGlobalConfigService
                .getWechatGlobalConfig(zeroWebConfig.getWechatAccount());

        provider.setAppId(config.getAppId());
        provider.setSecret(config.getAppSecret());

        final WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(provider);

        try {
            return wxMpService.getAccessToken();
        } catch (WxErrorException e) {
            logger.error("Failed to get access token", e);
            throw new RuntimeException("Internal server error, can't obtain the access token");
        }

    }
}
