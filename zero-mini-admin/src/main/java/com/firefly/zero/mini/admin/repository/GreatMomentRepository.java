/**
 * File: GreatMomentRepository
 * Author: DorSey Q F TANG
 * Created: 2020/3/14 10:16
 * CopyRight: All rights reserved
 */
package com.firefly.zero.mini.admin.repository;

import com.firefly.zero.mini.admin.entity.GreatMoment;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("greatMomentRepository")
public interface GreatMomentRepository {

    String TABLE = "great_moment";

    @Results(id = "gmRM", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.TINYINT),
            @Result(property = "thumbnailPath", column = "thumbnail_path", jdbcType = JdbcType.VARCHAR),
            @Result(property = "brief", column = "brief", jdbcType = JdbcType.VARCHAR)
    })
    @SelectProvider(type = GreatMomentSQLProvider.class, method = "queryForList")
    List<GreatMoment> queryForList();

    // ------
    // SQL Provider
    // ------------
    class GreatMomentSQLProvider {
        public String queryForList() {
            return new SQL() {
                {
                    SELECT("*");
                    FROM(TABLE);
                }
            }.toString();
        }
    }
}
