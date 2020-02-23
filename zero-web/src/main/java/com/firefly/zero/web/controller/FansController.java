/**
 * File: FansController
 * Author: DorSey Q F TANG
 * Created: 2020/2/19 11:45
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.controller;

import com.firefly.zero.web.Response.Response;
import com.firefly.zero.web.exception.ApiInvocationException;
import com.firefly.zero.web.model.Fans;
import com.firefly.zero.web.model.Pagination;
import com.firefly.zero.web.service.FansService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FansController extends BasicController {

    static final Logger logger = LoggerFactory.getLogger(FansController.class);
    private final FansService fansService;

    @Autowired
    public FansController(@Qualifier("fansService") final FansService fansService) {
        this.fansService = fansService;
    }

    @RequestMapping(value = "/fans", method = RequestMethod.GET)
    public String fans() {
        return "fans";
    }

    @ResponseBody
    @RequestMapping(value = "/fans", method = RequestMethod.POST)
    public Pagination<Fans> paginate(@RequestParam(value = "group_id", required = false) final Long groupId,
            @RequestParam(value = "country", required = false) final String country,
            @RequestParam(value = "province", required = false) final String province,
            @RequestParam(value = "city", required = false) final String city,
            @RequestParam(value = "start", defaultValue = "0") final int offset,
            @RequestParam(value = "length", defaultValue = "10") final int pageSize) {
        Pagination<Fans> fans = fansService.paginate(groupId, city, country, province, offset, pageSize);

        return fans;
    }

    @ResponseBody
    @RequestMapping(value = "/fans/{open_id}", method = RequestMethod.POST)
    public Response updateRemark(@RequestParam("open_id") final String openId, @RequestParam("remark") final String remark) {
        Response response;
        try {
            fansService.updateRemark(openId, remark);
            response = Response.ok();
        } catch (ApiInvocationException e) {
            logger.error("Failed to update the remark to {} of fans {}", remark, openId, e);
            response = Response.fail(e.getErrorCode() + ", " + e.getMessage());
        }

        return response;
    }
}
