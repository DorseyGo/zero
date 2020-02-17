/**
 * File: WeChatGlobalConfigDao
 * Author: DorSey Q F TANG
 * Created: 2020/2/9 10:45
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.dao;

import com.firefly.zero.web.model.WeChatGlobalConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("wechatGlobalConfigDao")
public interface WeChatGlobalConfigDao {

    @Results(id = "wechatGlobalConfigRM", value = {
            @Result(column = "wechat_account", property = "wechatAccount", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_id", property = "appId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "app_secret", property = "appSecret", jdbcType = JdbcType.VARCHAR),
            @Result(column = "encoding_aes_key", property = "encodingAESKey", jdbcType = JdbcType.VARCHAR),
            @Result(column = "token", property = "token", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP)
    })
    @SelectProvider(type = WeChatGlobalConfigSqlProvider.class, method = "queryByWeChatAccount")
    WeChatGlobalConfig queryByWeChatAccount(@Param("wechatAccount") final String wechatAccount);

    /**
     * The SQL provider for table <tt>wechat_global_config</tt>.
     */
    class WeChatGlobalConfigSqlProvider {
        static final String TABLE_NAME = "wechat_global_config";

        public String queryByWeChatAccount(final Map<String, Object> params) {
            return new SQL() {
                {
                    SELECT("*");
                    FROM(TABLE_NAME);
                    WHERE("wechat_account = #{wechatAccount}");
                }
            }.toString();
        }
    }
}
