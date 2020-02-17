/**
 * File: AccessTokenManager
 * Author: DorSey Q F TANG
 * Created: 2020/2/5 15:19
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service;

/**
 * The interface, for fetching access token from WeChat server.
 * <p>
 * it provides only single one method <tt>getAccessToken</tt> to fetch the access token from WeChat server.
 * </p>
 */
public interface AccessTokenManager {
    String getAccessToken();
}
