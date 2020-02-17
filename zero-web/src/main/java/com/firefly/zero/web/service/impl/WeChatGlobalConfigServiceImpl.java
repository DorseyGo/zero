/**
 * File: WeChatGlobalConfigServiceImpl
 * Author: DorSey Q F TANG
 * Created: 2020/2/9 10:44
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service.impl;

import com.firefly.zero.web.dao.WeChatGlobalConfigDao;
import com.firefly.zero.web.model.WeChatGlobalConfig;
import com.firefly.zero.web.service.WeChatGlobalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("wechatGlobalConfigService")
public class WeChatGlobalConfigServiceImpl implements WeChatGlobalConfigService {

    private final WeChatGlobalConfigDao weChatGlobalConfigDao;

    @Autowired
    public WeChatGlobalConfigServiceImpl(
            @Qualifier("wechatGlobalConfigDao") final WeChatGlobalConfigDao weChatGlobalConfigDao) {
        this.weChatGlobalConfigDao = weChatGlobalConfigDao;
    }

    @Override
    public WeChatGlobalConfig getWechatGlobalConfig(final String wechatAccount) {
        return weChatGlobalConfigDao.queryByWeChatAccount(wechatAccount);
    }
}
