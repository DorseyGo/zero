/**
 * File: MenuDao
 * Author: DorSey Q F TANG
 * Created: 2020/2/21 15:13
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.dao;

import com.firefly.zero.web.model.Menu;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("menuDao")
public interface MenuDao {

    @ResultType(Integer.class)
    @SelectProvider(type = MenuSQLProvider.class, method = "count")
    int count(@Param("startTime") final Date startTime, @Param("endTime") final Date endTime,
            @Param("name") final String name);

    @ResultMap("menuRM")
    @SelectProvider(type = MenuSQLProvider.class, method = "queryForSubList")
    List<Menu> queryForSubList(@Param("startTime") final Date startTime, @Param("endTime") final Date endTime,
            @Param("name") String name, @Param("offset") final int offset, @Param("limit") final int limit);

    @Results(id = "menuRM", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "type", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "key", property = "key", jdbcType = JdbcType.VARCHAR),
            @Result(column = "url", property = "url", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP)
    })
    @SelectProvider(type = MenuSQLProvider.class, method = "queryForSubMenuListById")
    List<Menu> queryForSubMenuListById(@Param("id") final int id);

    // ~~~ SQL provider
    // --------------------------------------------
    class MenuSQLProvider {
        private static final String TABLE_NAME = "menu";

        public String count(final Map<String, Object> params) {
            return new SQL() {
                {
                    SELECT("COUNT(*)");
                    FROM(TABLE_NAME);

                    if (params.get("startTime") != null) {
                        WHERE("create_time >= #{startTime}");
                    }

                    if (params.get("endTime") != null) {
                        WHERE("create_time < #{endTime}");
                    }

                    if (params.get("name") != null) {
                        WHERE("name LIKE #{name}");
                    }
                }
            }.toString();
        }

        /**
         * <strong>Note:</strong> we only search for the root level menu.
         * Sub-menus are returned by other queries.
         */
        public String queryForSubList(final Map<String, Object> params) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM(TABLE_NAME);
                    WHERE("parent_id = 0");

                    if (params.get("startTime") != null) {
                        WHERE("create_time >= #{startTime}");
                    }

                    if (params.get("endTime") != null) {
                        WHERE("create_time < #{endTime}");
                    }

                    if (params.get("name") != null) {
                        WHERE("name LIKE #{name}");
                    }

                    ORDER_BY("create_time DESC LIMIT #{offset},#{limit}");
                }
            }.toString();
        }

        public String queryForSubMenuListById(final Map<String, Object> params) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM(TABLE_NAME);
                    WHERE("parent_id = #{id}");
                }
            }.toString();
        }
    }
}
