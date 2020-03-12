/**
 * File: TabsServiceImpl
 * Author: DORSEy Q F TANG
 * Created: 2020/3/11
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.service.impl;

import com.firefly.zero.mini.admin.conf.MiniAdminConfig;
import com.firefly.zero.mini.admin.entity.Tabs;
import com.firefly.zero.mini.admin.repository.TabsRepository;
import com.firefly.zero.mini.admin.service.TabsService;
import com.firefly.zero.mini.admin.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("tabsService")
public class TabsServiceImpl implements TabsService {

    private final TabsRepository tabsRepository;

    private final MiniAdminConfig config;

    @Autowired
    public TabsServiceImpl(@Qualifier("tabsRepository") final TabsRepository tabsRepository, final MiniAdminConfig config) {
        this.tabsRepository = tabsRepository;
        this.config  = config;
    }

    @Override
    public List<Tabs> getAllTabs() {
        final List<Tabs> tabs = tabsRepository.queryForAll();
        Collections.sort(tabs);
        tabs.forEach(tab -> tab.setLeadingImgUrl(Utils.concat(config.getImageServerUrl(), tab.getLeadingImgUrl())));

        return tabs;
    }

}
