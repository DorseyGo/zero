/**
 * File: WeChatDeleteAllApi
 * Author: DorSey Q F TANG
 * Created: 2020/2/21 10:25
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.api;

import com.firefly.zero.web.exception.ApiInvocationException;

public interface WeChatDeleteAllApi<T> {
    T deletAll() throws ApiInvocationException;
}
