/**
 * File: FansService
 * Author: DorSey Q F TANG
 * Created: 2020/2/19 12:17
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service;

import com.firefly.zero.web.exception.ApiInvocationException;
import com.firefly.zero.web.model.Fans;
import com.firefly.zero.web.model.Pagination;

import java.util.List;

public interface FansService {
    boolean batchSave(final List<Fans> fans);

    Pagination<Fans> paginate(final Long groupId, final String city, final String country, final String province,
            final int offset, final int pageSize);

    void updateRemark(final String openId, final String remark) throws ApiInvocationException;
}
