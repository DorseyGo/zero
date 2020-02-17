/**
 * File: SysRoleServiceImpl
 * Author: DorSey Q F TANG
 * Created: 2020/2/12 18:00
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service.impl;

import com.firefly.zero.web.dao.SysRoleDao;
import com.firefly.zero.web.model.SysRole;
import com.firefly.zero.web.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

    private final SysRoleDao sysRoleDao;

    @Autowired
    public SysRoleServiceImpl(@Qualifier("sysRoleDao") final SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
    }

    @Override
    public SysRole getRoleByUserId(final long userId) {
        return sysRoleDao.queryByUserId(userId);
    }

}
