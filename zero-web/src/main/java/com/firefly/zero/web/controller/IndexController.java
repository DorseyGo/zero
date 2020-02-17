/**
 * File: IndexController
 * Author: DorSey Q F TANG
 * Created: 2020/2/12 15:35
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/dashboard")
    public String indexExpr() {
        return "dashboard";
    }
}
