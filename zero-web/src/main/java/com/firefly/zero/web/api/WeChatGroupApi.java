/**
 * File: WeChatGroupApi
 * Author: DorSey Q F TANG
 * Created: 2020/2/14 15:11
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.api;

import com.firefly.zero.web.exception.ApiInvocationException;
import com.firefly.zero.web.request.CreateGroupRequest;
import com.firefly.zero.web.request.DeleteGroupRequest;
import com.firefly.zero.web.request.UpdateGroupRequest;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import me.chanjar.weixin.mp.bean.WxMpGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("weChatGroupApi")
public class WeChatGroupApi extends WeChatApi implements WeChatCreationApi<WxMpGroup, CreateGroupRequest>,
        WeChatUpdationApi<Void, UpdateGroupRequest>, WeChatDeleteApi<Void, DeleteGroupRequest> {

    static final String DELETE_TAG_URI = "https://api.weixin.qq.com/cgi-bin/tags/delete";
    static final Logger logger = LoggerFactory.getLogger(WeChatGroupApi.class);

    /**
     * Empty constructor of {@link WeChatGroupApi}.
     */
    public WeChatGroupApi() {
        super();
    }

    @Override
    public WxMpGroup create(final CreateGroupRequest request) throws ApiInvocationException {

        try {
            return newWxMpService().groupCreate(request.getName());
        } catch (WxErrorException e) {
            logger.error("Failed to create group by issuing request: {}, due to error: {}", request, e.getError().getErrorMsg());
            throw newApiInvocationException(e);
        }
    }


    @Override
    public Void update(final UpdateGroupRequest request) throws ApiInvocationException {
        final WxMpGroup group = new WxMpGroup();
        group.setId(request.getId());
        group.setName(request.getName());

        try {
            newWxMpService().groupUpdate(group);
            return null;
        } catch (WxErrorException e) {
            logger.error("Failed to update the group by issuing request: {}, due to error: {}", request, e.getError().getErrorMsg());
            throw newApiInvocationException(e);
        }
    }

    @Override
    public Void delete(final DeleteGroupRequest request) throws ApiInvocationException {
        final WxMpGroup group = new WxMpGroup();
        group.setId(request.getId());

        try {
            newWxMpService().execute(new SimplePostRequestExecutor(), DELETE_TAG_URI, group.toJson());
            return null;
        } catch (WxErrorException e) {
            logger.error("Failed to delete the group by issuing request: {}, due to error: {}", request, e.getError().getErrorMsg());
            throw newApiInvocationException(e);
        }
    }
}
