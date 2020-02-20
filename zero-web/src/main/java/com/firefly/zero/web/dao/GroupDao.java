/**
 * File: GroupDao
 * Author: DorSey Q F TANG
 * Created: 2020/2/14 15:03
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.dao;

import com.firefly.zero.web.model.Group;
import com.google.common.base.Strings;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("groupDao")
public interface GroupDao {

    @Results(id = "groupRM", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "num_fans", property = "numFans", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "last_modified_time", property = "lastModifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    @SelectProvider(type = GroupSQLProvider.class, method = "queryById")
    Group queryById(@Param("id") final long id);

    @InsertProvider(type = GroupSQLProvider.class, method = "saveGroup")
    void saveGroup(@Param("group") final Group group);

    @UpdateProvider(type = GroupSQLProvider.class, method = "updateGroupById")
    void updateGroupById(@Param("id") final long id, @Param("name") final String name);

    @DeleteProvider(type = GroupSQLProvider.class, method = "deleteGroupById")
    void deleteGroupById(@Param("id") final long id);

    @ResultType(Integer.class)
    @SelectProvider(type = GroupSQLProvider.class, method = "count")
    int count(@Param("name") final String name, @Param("startTime") final Date startTime,
            @Param("endTime") final Date endTime);

    @ResultMap("groupRM")
    @SelectProvider(type = GroupSQLProvider.class, method = "queryForSubList")
    List<Group> queryForSubList(@Param("name") final String name, @Param("startTime") final Date startTime,
            @Param("endTime") final Date endTime,
            @Param("offset") final int offset,
            @Param("limit") final int limit);

    // ~~~ SQL provider
    // ------------------------------------------------
    class GroupSQLProvider {
        static final String TABLE_NAME = "`group`";

        public String queryById(final Map<String, Object> params) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM(TABLE_NAME);
                    WHERE("id = #{id}");
                }
            }.toString();
        }

        public String saveGroup(final Group group) {
            return new SQL() {
                {
                    INSERT_INTO(TABLE_NAME);
                    VALUES("id", "#{id}");
                    VALUES("num_fans", "#{numFans}");

                    if (!Strings.isNullOrEmpty(group.getName())) {
                        VALUES("name", "#{name}");
                    }

                }
            }.toString();
        }

        public String updateGroupById(final Map<String, Object> params) {
            return new SQL() {
                {
                    UPDATE(TABLE_NAME);
                    SET("name = #{name}");
                    WHERE("id = #{id}");
                }
            }.toString();
        }

        public String deleteGroupById(final Map<String, Object> params) {
            return new SQL() {
                {
                    DELETE_FROM(TABLE_NAME);
                    WHERE("id = #{id}");
                }
            }.toString();
        }

        public String count(final Map<String, Object> params) {
            return new SQL() {
                {
                    SELECT("COUNT(id)");
                    FROM(TABLE_NAME);

                    String condition = "1 = 1";
                    if (params.get("name") != null) {
                        condition += " AND name LIKE #{name}";
                    }

                    if (params.get("startTime") != null) {
                        condition += " AND create_time >= #{startTime}";
                    }

                    if (params.get("endTime") != null) {
                        condition += " AND create_time < #{endTime}";
                    }

                    WHERE(condition);
                }
            }.toString();
        }

        public String queryForSubList(final Map<String, Object> params) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM(TABLE_NAME);

                    String condition = "1 = 1";
                    if (params.get("name") != null) {
                        condition += " AND name LIKE #{name}";
                    }

                    if (params.get("startTime") != null) {
                        condition += " AND create_time >= #{startTime}";
                    }

                    if (params.get("endTime") != null) {
                        condition += " AND create_time < #{endTime}";
                    }
                    WHERE(condition);

                    ORDER_BY("create_time DESC LIMIT #{offset},#{limit}");
                }
            }.toString();
        }
    }
}
