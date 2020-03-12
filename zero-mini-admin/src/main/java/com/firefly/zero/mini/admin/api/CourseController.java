/**
 * File: CourseController
 * Author: DorSey Q F TANG
 * Created: 2020/3/12
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.api;

import com.firefly.zero.mini.admin.entity.CourseCategory;
import com.firefly.zero.mini.admin.response.Response;
import com.firefly.zero.mini.admin.service.CourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseCategoryService courseCategoryService;

    @Autowired
    public CourseController(@Qualifier("courseCategoryService") final CourseCategoryService courseCategoryService) {
        this.courseCategoryService = courseCategoryService;
    }

    @GetMapping("/course/categories")
    public Response<CourseCategory> getAvailableCategories() {
        final List<CourseCategory> categories = courseCategoryService.getAvailableCategories();
        return new Response<CourseCategory>().status(Response.SUCCESS).data(categories);
    }
}
