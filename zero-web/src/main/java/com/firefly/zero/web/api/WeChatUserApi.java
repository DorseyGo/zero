/**
 * File: WeChatUserApi
 * Author: DorSey Q F TANG
 * Created: 2020/2/20 09:55
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.api;

import com.firefly.zero.web.exception.ApiInvocationException;
import com.firefly.zero.web.request.UpdateRemarkRequest;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("weChatUserApi")
public class WeChatUserApi extends WeChatApi implements WeChatUpdationApi<Void, UpdateRemarkRequest> {

    static final Logger logger = LoggerFactory.getLogger(WeChatUserApi.class);

    @Override
    public Void update(final UpdateRemarkRequest request) throws ApiInvocationException {
        try {
            newWxMpService().userUpdateRemark(request.getOpenId(), request.getRemark());
        } catch (WxErrorException e) {
            logger.error("Failed to request to WeChat endpoint to update remark {} of fans {}", request.getRemark(),
                    request.getOpenId());
            throw newApiInvocationException(e);
        }

        return null;
    }
}
