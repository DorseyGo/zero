/**
 * File: CourseServiceImpl
 * Author: DorSey Q F TANG
 * Created: 2020/3/13
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.service.impl;

import com.firefly.zero.mini.admin.conf.MiniAdminConfig;
import com.firefly.zero.mini.admin.entity.Course;
import com.firefly.zero.mini.admin.repository.CourseRepository;
import com.firefly.zero.mini.admin.service.CourseService;
import com.firefly.zero.mini.admin.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final MiniAdminConfig config;

    @Autowired
    public CourseServiceImpl(@Qualifier("courseRepository") final CourseRepository courseRepository,
                             final MiniAdminConfig config) {
        this.courseRepository = courseRepository;
        this.config = config;
    }

    @Override
    public List<Course> getCoursesByCategoryId(final short categoryId) {
        final List<Course> courses = courseRepository.queryForListByCategoryId(categoryId);
        courses.forEach(course -> course.setImageUrl(Utils.concat(config.getImageServerUrl(), course.getImageUrl())));

        return courses;
    }
}
