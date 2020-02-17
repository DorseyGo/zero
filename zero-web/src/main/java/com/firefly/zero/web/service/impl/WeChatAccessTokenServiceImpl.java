/**
 * File: WeChatAccessTokenServiceImpl
 * Author: DorSey Q F TANG
 * Created: 2020/2/9 11:20
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service.impl;

import com.firefly.zero.web.dao.WeChatAccessTokenDao;
import com.firefly.zero.web.model.WeChatAccessToken;
import com.firefly.zero.web.service.WeChatAccessTokenService;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service("wechatAccessTokenService")
public class WeChatAccessTokenServiceImpl implements WeChatAccessTokenService {

    static final Logger logger = LoggerFactory.getLogger(WeChatAccessTokenServiceImpl.class);
    private final WeChatAccessTokenDao weChatAccessTokenDao;

    /**
     * Since the access token was updated every two hours, here
     * store it in memory as we can fetch it as soon as possible.
     */
    private final Map<String, String> CACHE;

    @Autowired
    public WeChatAccessTokenServiceImpl(
            @Qualifier("weChatAccessTokenDao") final WeChatAccessTokenDao weChatAccessTokenDao) {
        this.weChatAccessTokenDao = weChatAccessTokenDao;
        CACHE = Maps.newHashMap();
    }

    @Override
    public void saveWeChatAccessToken(final WeChatAccessToken weChatAccessToken) {
        CACHE.put(weChatAccessToken.getWechatAccount(), weChatAccessToken.getAccessToken());
        weChatAccessTokenDao.save(weChatAccessToken);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateAccessToken(final String wechatAccount, final String accessToken) {
        CACHE.put(wechatAccount, accessToken);
        weChatAccessTokenDao.updateAccessToken(wechatAccount, accessToken);
    }

    @Override
    public String getAccessTokenByAccount(final String wechatAccount) {
        String accessToken = CACHE.get(wechatAccount);
        if (accessToken == null) {
            // not in cache, try to fetch it from repository
            final WeChatAccessToken wechatAccessToken = getWeChatAccessTokenByAccount(wechatAccount);
            if (wechatAccessToken == null) {
                logger.warn("No access token fetched, pls check the scheduled job");
                throw new RuntimeException("Access token missed");
            }

            accessToken = wechatAccessToken.getAccessToken();
        }

        return accessToken;
    }

    @Override
    public WeChatAccessToken getWeChatAccessTokenByAccount(final String account) {
        return weChatAccessTokenDao.queryByAccount(account);
    }
}
