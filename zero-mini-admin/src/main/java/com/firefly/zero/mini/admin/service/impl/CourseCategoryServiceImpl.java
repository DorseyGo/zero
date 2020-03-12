/**
 * File: CourseCategoryServiceImpl
 * Author: DorSey Q F TANG
 * Created: 2020/3/12
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.service.impl;

import com.firefly.zero.mini.admin.entity.CourseCategory;
import com.firefly.zero.mini.admin.repository.CourseCategoryRepository;
import com.firefly.zero.mini.admin.service.CourseCategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseCategoryService")
public class CourseCategoryServiceImpl implements CourseCategoryService {
    private final CourseCategoryRepository courseCategoryRepository;

    public CourseCategoryServiceImpl(@Qualifier("courseCategoryRepository") final CourseCategoryRepository courseCategoryRepository) {
        this.courseCategoryRepository = courseCategoryRepository;
    }

    @Override
    public List<CourseCategory> getAvailableCategories() {
        return courseCategoryRepository.queryForAll();
    }
}
