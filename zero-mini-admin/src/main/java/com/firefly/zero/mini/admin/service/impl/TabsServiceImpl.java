/**
 * File: TabsServiceImpl
 * Author: DORSEy Q F TANG
 * Created: 2020/3/11
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.service.impl;

import com.firefly.zero.mini.admin.entity.Tabs;
import com.firefly.zero.mini.admin.repository.TabsRepository;
import com.firefly.zero.mini.admin.service.TabsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tabsService")
public class TabsServiceImpl implements TabsService {

    private final TabsRepository tabsRepository;

    @Autowired
    public TabsServiceImpl(@Qualifier("tabsRepository") final TabsRepository tabsRepository) {
        this.tabsRepository = tabsRepository;
    }

    @Override
    public List<Tabs> getAllTabs() {
        return tabsRepository.queryForAll();
    }
}
