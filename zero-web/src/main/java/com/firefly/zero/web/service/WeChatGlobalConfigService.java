/**
 * File: WeChatGlobalConfigService
 * Author: DorSey Q F TANG
 * Created: 2020/2/9 10:42
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service;

import com.firefly.zero.web.model.WeChatGlobalConfig;

public interface WeChatGlobalConfigService {
    WeChatGlobalConfig getWechatGlobalConfig(final String wechatAccount);
}
