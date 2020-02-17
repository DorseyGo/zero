/**
 * File: SysUserServiceImpl
 * Author: DorSey Q F TANG
 * Created: 2020/2/12 17:39
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service.impl;

import com.firefly.zero.web.dao.SysUserDao;
import com.firefly.zero.web.model.SysUser;
import com.firefly.zero.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

    private final SysUserDao sysUserDao;

    @Autowired
    public SysUserServiceImpl(@Qualifier("sysUserDao") final SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    @Override
    public SysUser getUserByUsername(final String username) {
        return sysUserDao.queryByUsername(username);
    }
}
