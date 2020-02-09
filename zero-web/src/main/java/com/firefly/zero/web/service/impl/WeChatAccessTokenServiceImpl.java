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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("wechatAccessTokenService")
public class WeChatAccessTokenServiceImpl implements WeChatAccessTokenService {

    private final WeChatAccessTokenDao weChatAccessTokenDao;

    @Autowired
    public WeChatAccessTokenServiceImpl(
            @Qualifier("weChatAccessTokenDao") final WeChatAccessTokenDao weChatAccessTokenDao) {
        this.weChatAccessTokenDao = weChatAccessTokenDao;
    }

    @Override
    public void saveWeChatAccessToken(final WeChatAccessToken weChatAccessToken) {
        weChatAccessTokenDao.save(weChatAccessToken);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void updateAccessToken(String uuid, String accessToken) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(uuid), "UUID for WeChatAccessToken MUST not be null or empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(accessToken), "Access token MUST not be null or empty");

        weChatAccessTokenDao.updateAccessToken(uuid, accessToken);
    }

    @Override
    public List<WeChatAccessToken> getAllAccessTokens() {
        return weChatAccessTokenDao.queryForList();
    }

}
