/**
 * File: MenuController
 * Author: DorSey Q F TANG
 * Created: 2020/2/19 10:39
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.controller;

import com.firefly.zero.web.model.Menu;
import com.firefly.zero.web.model.Pagination;
import com.firefly.zero.web.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * For handling the customized menu request.
 */
@Controller
public class MenuController extends BasicController {

    private final MenuService menuService;

    @Autowired
    public MenuController(@Qualifier("menuService") final MenuService menuService) {
        this.menuService = menuService;
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu() {
        return "menus";
    }

    @ResponseBody
    @RequestMapping(value = "/menus", method = RequestMethod.POST)
    public Pagination<Menu> paginate(@RequestParam(value = "start_time", required = false) final Date startTime,
            @RequestParam(value = "end_time", required = false) final Date endTime,
            @RequestParam(value = "name", required = false) final String name, @RequestParam("start") final int offset,
            @RequestParam(value = "length", defaultValue = "10") final int pageSize) {
        Pagination<Menu> menus = menuService
                .paginate(startTime, endTime, appendWildCardIfPossible(name), offset, pageSize);

        return menus;
    }
}
