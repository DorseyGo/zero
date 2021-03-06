/**
 * File: WeChatAccessTokenService
 * Author: DorSey Q F TANG
 * Created: 2020/2/9 11:16
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service;

import com.firefly.zero.web.model.WeChatAccessToken;

import java.util.List;

public interface WeChatAccessTokenService {
    void saveWeChatAccessToken(final WeChatAccessToken weChatAccessToken);

    void updateAccessToken(final String wechatAccount, final String accessToken);

    String getAccessTokenByAccount(final String wechatAccount);

    WeChatAccessToken getWeChatAccessTokenByAccount(final String account);
}
