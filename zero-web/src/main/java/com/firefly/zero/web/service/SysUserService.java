/**
 * File: SysUserService
 * Author: DorSey Q F TANG
 * Created: 2020/2/12 17:34
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service;

import com.firefly.zero.web.model.SysUser;

public interface SysUserService {
    SysUser getUserByUsername(final String username);
}
