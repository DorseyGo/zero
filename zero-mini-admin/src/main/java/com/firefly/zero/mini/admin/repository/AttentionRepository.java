/**
 * File: AttentionRepository
 * Author: DorSey Q F TANG
 * Created: 2020/3/14 10:53
 * CopyRight: All rights reserved
 */
package com.firefly.zero.mini.admin.repository;

import com.firefly.zero.mini.admin.entity.Attention;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("attentionRepository")
public interface AttentionRepository {

    String TABLE = "attention";

    @SelectProvider(type = AttentionSQLProvider.class, method = "queryForList")
    List<Attention> queryForList();

    // -----
    // AttentionSQL provider
    // -----
    class AttentionSQLProvider {
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
