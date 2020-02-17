/**
 * File: Group
 * Author: DorSey Q F TANG
 * Created: 2020/2/14 14:56
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * The entity, which contains the properties for <tt>tag</tt> resource, also known as group in current system.
 */
public class Group {
    private long id;
    private String name;
    private long numFans;

    @JsonFormat(pattern = Constants.DEFAULT_DATE_PATTERN)
    private Date createTime;

    @JsonFormat(pattern = Constants.DEFAULT_DATE_PATTERN)
    private Date lastModifiedTime;

    /**
     * Empty constructor of {@link Group}.
     */
    public Group() {
        // empty
    }

    public Group(final long id, final String name, final long numFans) {
        setId(id);
        setName(name);
        setNumFans(numFans);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumFans() {
        return numFans;
    }

    public void setNumFans(final long numFans) {
        this.numFans = numFans;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != group.id) return false;
        if (numFans != group.numFans) return false;
        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        if (createTime != null ? !createTime.equals(group.createTime) : group.createTime != null) return false;
        return lastModifiedTime != null ? lastModifiedTime.equals(group.lastModifiedTime) : group.lastModifiedTime == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (numFans ^ (numFans >>> 32));
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastModifiedTime != null ? lastModifiedTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numFans=" + numFans +
                ", createTime=" + createTime +
                ", lastModifiedTime=" + lastModifiedTime +
                '}';
    }
}
