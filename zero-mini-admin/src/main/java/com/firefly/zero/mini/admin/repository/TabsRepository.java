/**
 * File: TabsRepository
 * Author: DORSEy Q F TANG
 * Created: 2020/3/11
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.repository;

import com.firefly.zero.mini.admin.entity.Tabs;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tabsRepository")
public interface TabsRepository {

    String TABLE = "tabs";

    @Results(id = "tabsRM", value = {
            @Result(property = "id", column = "id", jdbcType = JdbcType.TINYINT),
            @Result(property = "name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "leadingImgUrl", column = "leading_img_url", jdbcType = JdbcType.VARCHAR),
            @Result(property = "activateWhenInit", column = "activate_when_init", jdbcType = JdbcType.TINYINT),
            @Result(property = "component", column = "component", jdbcType = JdbcType.VARCHAR)
    })
    @SelectProvider(type = TabsSQLProvider.class, method = "queryForAll")
    List<Tabs> queryForAll();

    // ----------
    // SQL provider
    // -----------
    class TabsSQLProvider {

        public String queryForAll() {
            return new SQL() {
                {
                    SELECT("id", "name", "leading_img_url", "activate_when_init", "component");
                    FROM(TABLE);
                    ORDER_BY("id");
                }
            }.toString();
        }
    }
}
