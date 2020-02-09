/**
 * File: GetAccessTokenServiceJob
 * Author: DorSey Q F TANG
 * Created: 2020/2/5 15:28
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.job;

import com.firefly.zero.web.model.WeChatAccessToken;
import com.firefly.zero.web.service.AccessTokenManager;
import com.firefly.zero.web.service.WeChatAccessTokenService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GetAccessTokenServiceJob extends QuartzJobBean {
    static Logger logger = LoggerFactory.getLogger(GetAccessTokenServiceJob.class);

    private final AccessTokenManager accessTokenManager;
    private final WeChatAccessTokenService weChatAccessTokenService;

    @Autowired
    public GetAccessTokenServiceJob(
            @Qualifier("accessTokenManager") final AccessTokenManager accessTokenManager,
            @Qualifier("wechatAccessTokenService") final WeChatAccessTokenService weChatAccessTokenService) {
        this.accessTokenManager = accessTokenManager;
        this.weChatAccessTokenService = weChatAccessTokenService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        final String accessToken = accessTokenManager.getAccessToken();
        logger.debug("Access token obtained is {}", accessToken);

        final List<WeChatAccessToken> wechatAccessTokens = weChatAccessTokenService.getAllAccessTokens();
        WeChatAccessToken weChatAccessToken = null;
        if (wechatAccessTokens == null || wechatAccessTokens.isEmpty()) {
            final String uuid = UUID.randomUUID().toString();
            weChatAccessToken = new WeChatAccessToken(uuid, accessToken);

            weChatAccessTokenService.saveWeChatAccessToken(weChatAccessToken);
            return;
        }

        weChatAccessToken = wechatAccessTokens.get(0);
        weChatAccessTokenService.updateAccessToken(weChatAccessToken.getUuid(), accessToken);
    }
}
