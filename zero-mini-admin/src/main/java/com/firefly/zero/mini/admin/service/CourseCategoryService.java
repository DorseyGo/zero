/**
 * File: CourseCategoryService
 * Author: DorSey Q F TANG
 * Created: 2020/3/12
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.service;

import com.firefly.zero.mini.admin.entity.CourseCategory;

import java.util.List;

public interface CourseCategoryService {
    List<CourseCategory> getAvailableCategories();
}
