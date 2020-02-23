/**
 * File: MenuServiceImpl
 * Author: DorSey Q F TANG
 * Created: 2020/2/21 15:11
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service.impl;

import com.firefly.zero.web.api.WeChatMenuApi;
import com.firefly.zero.web.dao.MenuDao;
import com.firefly.zero.web.exception.ApiInvocationException;
import com.firefly.zero.web.model.Menu;
import com.firefly.zero.web.model.Pagination;
import com.firefly.zero.web.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    private final MenuDao menuDao;
    private final WeChatMenuApi weChatMenuApi;

    public MenuServiceImpl(@Qualifier("menuDao") final MenuDao menuDao,
            @Qualifier("weChatMenuApi") final WeChatMenuApi weChatMenuApi) {
        this.menuDao = menuDao;
        this.weChatMenuApi = weChatMenuApi;
    }

    @Override
    public void createMenu(com.firefly.zero.web.struct.Menu menu) throws ApiInvocationException {

    }

    @Override
    public Pagination<Menu> paginate(Date startTime, Date endTime, String name, int offset,
            int pageSize) {
        final int totalRecords = menuDao.count(startTime, endTime, name);
        if (totalRecords == 0) {
            logger.info("No records found by conditions: startTime {}, endTime{}, name{}", startTime, endTime, name);
            return new Pagination<>();
        }

        final int curPage = (offset / pageSize) + 1;
        final List<Menu> menus = menuDao.queryForSubList(startTime, endTime, name, offset, pageSize);
        return new Pagination<>(curPage, pageSize, totalRecords, menus);
    }

    @Override
    public List<Menu> getSubMenusById(final int id) {
        return menuDao.queryForSubMenuListById(id);
    }
}
