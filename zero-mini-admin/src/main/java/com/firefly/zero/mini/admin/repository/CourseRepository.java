/**
 * File: CourseRepository
 * Author: DorSey Q F TANG
 * Created: 2020/3/13
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.repository;

import com.firefly.zero.mini.admin.entity.Course;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("courseRepository")
public interface CourseRepository {

    String TABLE = "course";

    @Results(id = "courseRM", value = {
            @Result(property = "id", column = "id", jdbcType = JdbcType.TINYINT),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "brief", column = "brief", jdbcType = JdbcType.VARCHAR),
            @Result(property = "imageUrl", column = "img_relative_path", jdbcType = JdbcType.VARCHAR)
    })
    @SelectProvider(type = CourseSQLProvider.class, method = "queryForListByCategoryId")
    List<Course> queryForListByCategoryId(@Param("categoryId") final short categoryId);

    // -----
    // SQL provider
    // ---------
    class CourseSQLProvider {
        public String queryForListByCategoryId(final Map<String, Object> params) {
            return new SQL(){
                {
                    SELECT("id", "name", "brief", "img_relative_path");
                    FROM(TABLE);
                    if (params.get("categoryId") != null) {
                        WHERE("category_id = #{categoryId}");
                    }
                }
            }.toString();
        }
    }
}
