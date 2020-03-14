/**
 * File: GreatMomentController
 * Author: DorSey Q F TANG
 * Created: 2020/3/14 10:10
 * CopyRight: All rights reserved
 */
package com.firefly.zero.mini.admin.api;

import com.firefly.zero.mini.admin.entity.GreatMoment;
import com.firefly.zero.mini.admin.response.Response;
import com.firefly.zero.mini.admin.service.GreatMomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GreatMomentController {

    private final GreatMomentService greatMomentService;

    @Autowired
    public GreatMomentController(@Qualifier("greatMomentService") final GreatMomentService greatMomentService) {
        this.greatMomentService = greatMomentService;
    }

    @GetMapping("/great-moments")
    public Response<GreatMoment> getGreatMoments() {
        final List<GreatMoment> greatMoments = greatMomentService.getAllGreatMoments();

        return new Response<GreatMoment>().status(Response.SUCCESS).data(greatMoments);
    }
}
