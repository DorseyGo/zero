/**
 * File: MenuService
 * Author: DorSey Q F TANG
 * Created: 2020/2/21 10:56
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service;

import com.firefly.zero.web.exception.ApiInvocationException;
import com.firefly.zero.web.model.Menu;
import com.firefly.zero.web.model.Pagination;

import java.util.Date;
import java.util.List;

public interface MenuService {
    void createMenu(final com.firefly.zero.web.struct.Menu menu) throws ApiInvocationException;

    Pagination<Menu> paginate(final Date startTime, final Date endTime, final String name, final int offset, final int pageSize);

    List<Menu> getSubMenusById(final int id);
}
