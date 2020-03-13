/**
 * File: NotificationRepository
 * Author: DorSey Q F TANG
 * Created: 2020/3/13
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.repository;

import com.firefly.zero.mini.admin.entity.Notification;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("notificationRepository")
public interface NotificationRepository {

    String TABLE = "notification";

    @SelectProvider(type = NotificationSQLProvider.class, method = "queryForList")
    List<Notification> queryForList(@Param("enable") int enable);

    // -----
    // SQL provider
    // -------
    class NotificationSQLProvider {
        public String queryForList(final Map<String, Object> params) {
            return new SQL() {
                {
                    SELECT("id", "slide");
                    FROM(TABLE);
                    if (params.get("enable") != null) {
                        WHERE("enable = #{enable}");
                    }

                    ORDER_BY("last_modified_time DESC");
                }
            }.toString();
        }
    }
}
