/**
 * File: SysRoleDao
 * Author: DorSey Q F TANG
 * Created: 2020/2/12 18:01
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.dao;

import com.firefly.zero.web.model.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("sysRoleDao")
public interface SysRoleDao {

    @Results(id = "sysRoleRM", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
            @Result(column = "role", property = "role", jdbcType = JdbcType.VARCHAR)
    })
    @SelectProvider(type = SysRoleSqlProvider.class, method = "queryByUserId")
    SysRole queryByUserId(@Param("userId") final long userId);

    // ~~~ SQL provider
    // ------------------------------------------------------
    class SysRoleSqlProvider {
        static final String TABLE_NAME = "sys_role";
        static final String JOINED_TABLE_NAME = "user_role";

        public String queryByUserId(final Map<String, Object> params) {
            return new SQL() {
                {
                    SELECT("role.id, role.role");
                    FROM(TABLE_NAME + " as role");
                    INNER_JOIN(JOINED_TABLE_NAME + " as ur ON role.id = ur.role_id AND ur.user_id = #{userId}");
                }
            }.toString();
        }

    }
}
