/**
 * File: CourseService
 * Author: DorSey Q F TANG
 * Created: 2020/3/13
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.service;

import com.firefly.zero.mini.admin.entity.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCoursesByCategoryId(short categoryId);
}
