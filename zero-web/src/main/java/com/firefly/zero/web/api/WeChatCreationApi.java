/**
 * File: WeChatCreationApi
 * Author: DorSey Q F TANG
 * Created: 2020/2/17 17:28
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.api;

import com.firefly.zero.web.exception.ApiInvocationException;
import com.firefly.zero.web.request.Request;

public interface WeChatCreationApi<T, REQ extends Request> {
    T create(final REQ request) throws ApiInvocationException;
}
