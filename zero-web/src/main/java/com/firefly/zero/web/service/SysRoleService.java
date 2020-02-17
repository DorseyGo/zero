/**
 * File: SysRoleService
 * Author: DorSey Q F TANG
 * Created: 2020/2/12 17:58
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service;

import com.firefly.zero.web.model.SysRole;

public interface SysRoleService {
    SysRole getRoleByUserId(final long id);

}
