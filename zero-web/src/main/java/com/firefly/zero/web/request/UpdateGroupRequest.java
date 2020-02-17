/**
 * File: UpdateGroupRequest
 * Author: DorSey Q F TANG
 * Created: 2020/2/17 17:31
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.request;

/**
 * Request sent to update the specified group.
 */
public class UpdateGroupRequest extends DeleteGroupRequest implements Request {

    private final String name;

    public UpdateGroupRequest(final long id, final String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UpdateGroupRequest{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
