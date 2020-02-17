/**
 * File: SysUser
 * Author: DorSey Q F TANG
 * Created: 2020/2/12 17:36
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.model;

import java.io.Serializable;

public class SysUser implements Serializable {

    private long id;
    private String username;
    private String password;
    private int enabled;

    /**
     * Empty constructor of {@link SysUser}.
     */
    public SysUser() {
        // empty
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysUser sysUser = (SysUser) o;

        if (id != sysUser.id) return false;
        if (enabled != sysUser.enabled) return false;
        if (username != null ? !username.equals(sysUser.username) : sysUser.username != null) return false;
        return password != null ? password.equals(sysUser.password) : sysUser.password == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + enabled;
        return result;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
