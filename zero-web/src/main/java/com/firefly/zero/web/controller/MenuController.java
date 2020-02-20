/**
 * File: MenuController
 * Author: DorSey Q F TANG
 * Created: 2020/2/19 10:39
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * For handling the customized menu request.
 */
@Controller
public class MenuController {

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu() {
        return "menus";
    }
}
