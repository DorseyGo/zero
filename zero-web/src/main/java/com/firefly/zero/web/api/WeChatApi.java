/**
 * File: WeChatApi
 * Author: DorSey Q F TANG
 * Created: 2020/2/14 15:26
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.api;

import com.firefly.zero.web.config.ZeroWebConfig;
import com.firefly.zero.web.exception.ApiInvocationException;
import com.firefly.zero.web.request.Request;
import com.firefly.zero.web.service.WeChatAccessTokenService;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class WeChatApi {

    @Autowired
    @Qualifier("wechatAccessTokenService")
    protected WeChatAccessTokenService weChatAccessTokenService;

    @Autowired
    protected ZeroWebConfig config;

    /**
     * Empty constructor of {@link WeChatApi}.
     */
    protected WeChatApi() {
        // empty
    }

    protected WxMpService newWxMpService() {
        final String account = config.getWechatAccount();
        final String accessToken = this.weChatAccessTokenService.getAccessTokenByAccount(account);
        final WxMpInMemoryConfigStorage wxConfig = new WxMpInMemoryConfigStorage();
        wxConfig.setAccessToken(accessToken);

        final WxMpService service = new WxMpServiceImpl();
        service.setWxMpConfigStorage(wxConfig);

        return service;
    }

    protected ApiInvocationException newApiInvocationException(final WxErrorException e) {
        final WxError error = e.getError();
        return new ApiInvocationException(error.getErrorCode(), error.getErrorMsg(), e);
    }

    public ZeroWebConfig getConfig() {
        return config;
    }
}
