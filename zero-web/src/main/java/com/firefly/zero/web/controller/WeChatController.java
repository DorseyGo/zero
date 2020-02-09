/**
 * File: WeChatController
 * Author: DorSey Q F TANG
 * Created: 2020/2/5 10:53
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.controller;

import com.firefly.zero.web.model.WeChatGlobalConfig;
import com.firefly.zero.web.service.WeChatGlobalConfigService;
import com.google.common.base.Preconditions;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wechat")
public class WeChatController {

    private final WeChatGlobalConfigService weChatGlobalConfigService;

    @Autowired
    public WeChatController(
            @Qualifier("wechatGlobalConfigService") final WeChatGlobalConfigService weChatGlobalConfigService) {
        this.weChatGlobalConfigService = weChatGlobalConfigService;
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String checkSignature(@RequestParam("signature") final String signature,
            @RequestParam("timestamp") final String timestamp, @RequestParam("nonce") final String nonce,
            @RequestParam(value = "echostr", required = false) final String echo) {

        final WeChatGlobalConfig config = weChatGlobalConfigService.getWechatGlobalConfig();
        Preconditions.checkState(config != null, "No WeChat configuration found");
        final WxMpInMemoryConfigStorage storage = new WxMpInMemoryConfigStorage();
        storage.setToken(config.getToken());

        final WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(storage);

        boolean isValid = wxService.checkSignature(timestamp, nonce, signature);
        if (isValid) {
            return echo;
        }

        return null;
    }

}
