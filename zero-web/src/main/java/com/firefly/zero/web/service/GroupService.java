/**
 * File: GroupService
 * Author: DorSey Q F TANG
 * Created: 2020/2/14 14:58
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service;

import com.firefly.zero.web.exception.ApiInvocationException;
import com.firefly.zero.web.model.Group;
import com.firefly.zero.web.model.Pagination;

public interface GroupService {
    Group getGroupById(final long id);

    void deleteGroupById(final long id) throws ApiInvocationException;

    void updateGroup(final long id, final String name) throws ApiInvocationException;

    Pagination<Group> paginate(int offset, int pageSize);

    void saveGroup(final String name) throws ApiInvocationException;
}
