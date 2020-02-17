/**
 * File: CreateGroupRequest
 * Author: DorSey Q F TANG
 * Created: 2020/2/17 17:23
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.request;

public class CreateGroupRequest implements Request {
    protected final String name;

    public CreateGroupRequest(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CreateGroupRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
