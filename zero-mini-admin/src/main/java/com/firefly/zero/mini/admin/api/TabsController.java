/**
 * File: TabsController
 * Author: DORSEy Q F TANG
 * Created: 2020/3/11
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.api;

import com.firefly.zero.mini.admin.entity.Tabs;
import com.firefly.zero.mini.admin.response.Response;
import com.firefly.zero.mini.admin.service.TabsService;
import javafx.scene.control.Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TabsController {

    private final TabsService tabsService;

    @Autowired
    public TabsController(@Qualifier("tabsService") final TabsService tabsService) {
        this.tabsService = tabsService;
    }

    @GetMapping("/tabs")
    public Response<Tabs> getTabs() {
        final List<Tabs> data = tabsService.getAllTabs();
        return new Response<Tabs>().status(Response.SUCCESS).data(data);
    }
}
