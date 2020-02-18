/**
 * File: TagController
 * Author: DorSey Q F TANG
 * Created: 2020/2/14 17:06
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.controller;

import com.firefly.zero.web.Response.Response;
import com.firefly.zero.web.exception.ApiInvocationException;
import com.firefly.zero.web.model.Group;
import com.firefly.zero.web.model.PaginateGroupRequest;
import com.firefly.zero.web.model.Pagination;
import com.firefly.zero.web.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Locale;

@Controller
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(@Qualifier("groupService") final GroupService groupService) {
        this.groupService = groupService;
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public String group() {
        return "group-mgmt";
    }

    @ResponseBody
    @RequestMapping(value = "/groups", method = RequestMethod.POST)
    public Pagination<Group> pagination(@RequestParam("start") final int offset,
            @RequestParam(value = "length", defaultValue = "10") final int pageSize,
            @RequestParam(value = "name", required = false) final String name,
            @RequestParam(value = "start_time", required = false) final Date startTime,
            @RequestParam(value = "end_time", required = false) final Date endTime) {

        Pagination<Group> pagination = groupService.paginate(name, startTime, endTime, offset, pageSize);

        return pagination;
    }

    @ResponseBody
    @RequestMapping(value = "/groups/{id}", method = RequestMethod.DELETE)
    public Response deleteGroup(@PathVariable("id") final long id) {
        Response response = null;
        try {
            groupService.deleteGroupById(id);
            response = Response.ok();
        } catch (ApiInvocationException e) {
            response = Response.fail(String.format(Locale.ROOT,"code: %s, %s", e.getErrorCode(), e.getMessage()));
        }

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/groups/{id}", method = RequestMethod.POST)
    public Response updateGroup(@PathVariable("id") final long id, @RequestParam("name") final String name) {
        Response response = null;
        try {
            groupService.updateGroup(id, name);
            response = Response.ok();
        } catch (ApiInvocationException aie) {
            response = Response.fail(String.format(Locale.ROOT, "code: %s, %s", aie.getErrorCode(), aie.getMessage()));
        }

        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/group", method = RequestMethod.PUT)
    public Response addGroup(@RequestParam("group_name") final String name) {
        Response response = null;
        try {
            groupService.saveGroup(name);
            response = Response.ok();
        } catch (ApiInvocationException aie) {
            response = Response.fail(String.format(Locale.ROOT, "code: %s, %s", aie.getErrorCode(), aie.getMessage()));
        }

        return response;
    }
}
