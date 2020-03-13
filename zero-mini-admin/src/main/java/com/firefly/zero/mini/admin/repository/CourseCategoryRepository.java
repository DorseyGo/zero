/**
 * File: CourseCategoryRepository
 * Author: DorSey Q F TANG
 * Created: 2020/3/12
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.repository;

import com.firefly.zero.mini.admin.entity.CourseCategory;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("courseCategoryRepository")
public interface CourseCategoryRepository {
    String TABLE = "course_category";

    @Results(id = "cateRM", value = {
            @Result(property = "id", column = "id", jdbcType = JdbcType.TINYINT),
            @Result(property = "category", column = "category", jdbcType = JdbcType.VARCHAR),
            @Result(property = "active", column = "activate_when_init", jdbcType = JdbcType.TINYINT)
    })
    @SelectProvider(type = CourseCategorySQLProvider.class, method = "queryForAll")
    List<CourseCategory> queryForAll();

    // ---
    // SQL provider
    // ------------
    class CourseCategorySQLProvider {

        public String queryForAll() {
            return new SQL() {
                {
                    SELECT("*");
                    FROM(TABLE);
                }
            }.toString();
        }
    }
}
