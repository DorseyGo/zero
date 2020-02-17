/**
 * File: SysRole
 * Author: DorSey Q F TANG
 * Created: 2020/2/12 17:59
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.model;

public class SysRole {
    private int id;
    private String role;

    /**
     * Empty constructor of {@link SysRole}.
     */
    public SysRole() {
        // empty
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysRole sysRole = (SysRole) o;

        if (id != sysRole.id) return false;
        return role != null ? role.equals(sysRole.role) : sysRole.role == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}

