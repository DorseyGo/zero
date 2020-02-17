/**
 * File: DeleteGroupRequest
 * Author: DorSey Q F TANG
 * Created: 2020/2/17 17:33
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.request;

public class DeleteGroupRequest implements Request {
    protected final long id;

    public DeleteGroupRequest(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DeleteGroupRequest{" +
                "id=" + id +
                '}';
    }
}
