/**
 * File: ZeroUserDetailsService
 * Author: DorSey Q F TANG
 * Created: 2020/2/12 17:32
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.auth;

import com.firefly.zero.web.model.SysRole;
import com.firefly.zero.web.model.SysUser;
import com.firefly.zero.web.service.SysRoleService;
import com.firefly.zero.web.service.SysUserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("userDetailsService")
public class ZeroUserDetailsService implements UserDetailsService {

    private final SysUserService sysUserService;
    private final SysRoleService sysRoleService;

    @Autowired
    public ZeroUserDetailsService(@Qualifier("sysUserService") final SysUserService sysUserService,
            @Qualifier("sysRoleService") final SysRoleService sysRoleService) {
        this.sysUserService = sysUserService;
        this.sysRoleService = sysRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final SysUser sysUser = sysUserService.getUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("No corresponding user found according to username: " + username);
        }

        final Collection<GrantedAuthority> authorities = Lists.newArrayList();
        final SysRole sysRole = sysRoleService.getRoleByUserId(sysUser.getId());
        authorities.add(new SimpleGrantedAuthority(sysRole.getRole()));

        return new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
    }
}
