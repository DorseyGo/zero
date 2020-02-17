/**
 * File: SysUserDao
 * Author: DorSey Q F TANG
 * Created: 2020/2/12 17:40
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.dao;

import com.firefly.zero.web.model.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("sysUserDao")
public interface SysUserDao {

    @Results(id = "sysUserRM", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "enabled", property = "enabled", jdbcType = JdbcType.INTEGER)
    })
    @SelectProvider(type = SysUserSqlProvider.class, method = "queryByUsername")
    SysUser queryByUsername(@Param("username") final String username);

    // ~~~ SQL provider
    // ----------------------------------------------------------
    class SysUserSqlProvider {
        private static final String TABLE_NAME = "sys_user";

        public String queryByUsername(final Map<String, Object> params) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM(TABLE_NAME);
                    WHERE("username = #{username}", "enabled = 0");
                }
            }.toString();
        }
    }
}
