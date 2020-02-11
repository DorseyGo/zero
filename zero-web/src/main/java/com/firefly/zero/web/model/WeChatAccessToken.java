/**
 * File: WeChatAccessToken
 * Author: DorSey Q F TANG
 * Created: 2020/2/9 11:17
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.model;

import java.util.Date;

public class WeChatAccessToken {

    private String uuid;
    private String accessToken;
    private Date createTime;
    private Date lastModifiedTime;

    /**
     * Empty constructor of {@link WeChatAccessToken}.
     */
    public WeChatAccessToken() {
        // empty
    }

    /**
     * Constructor of {@link WeChatAccessToken}, with uuid and access token specified.
     *
     * @param uuid
     * @param accessToken
     */
    public WeChatAccessToken(final String uuid, final String accessToken) {
        this.uuid = uuid;
        this.accessToken = accessToken;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(final String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(final Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeChatAccessToken that = (WeChatAccessToken) o;

        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        if (accessToken != null ? !accessToken.equals(that.accessToken) : that.accessToken != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        return lastModifiedTime != null ? lastModifiedTime
                .equals(that.lastModifiedTime) : that.lastModifiedTime == null;
    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastModifiedTime != null ? lastModifiedTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeChatAccessToken{" +
                "uuid='" + uuid + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", createTime=" + createTime +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}
