/**
 * File: WeChatAccessTokenDao
 * Author: DorSey Q F TANG
 * Created: 2020/2/9 11:21
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.dao;

import com.firefly.zero.web.model.WeChatAccessToken;
import com.google.common.base.Strings;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("weChatAccessTokenDao")
public interface WeChatAccessTokenDao {

    @InsertProvider(type = WeChatAccessTokenSqlProvider.class, method = "save")
    void save(@Param("wechatAccessToken") final WeChatAccessToken weChatAccessToken);

    @UpdateProvider(type = WeChatAccessTokenSqlProvider.class, method = "updateAccessToken")
    void updateAccessToken(@Param("wechatAccount") final String wechatAccount, @Param("accessToken") final String accessToken);

    @Results(id = "wechatAccessTokenRM", value = {
            @Result(column = "wechat_account", property = "wechatAccount", jdbcType = JdbcType.VARCHAR),
            @Result(column = "access_token", property = "accessToken", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "last_modified_time", property = "lastModifiedTime", jdbcType = JdbcType.TIMESTAMP)
    })
    @SelectProvider(type = WeChatAccessTokenSqlProvider.class, method = "queryByAccount")
    WeChatAccessToken queryByAccount(@Param("wechatAccount") final String wechatAccount);

    // ~~~ SQL provider
    // ------------------------------------------------------------------------------------
    /**
     * The SQL provider for <tt>WeChatAccessToken</tt>.
     */
    class WeChatAccessTokenSqlProvider {
        private static final String TABLE_NAME = "wechat_access_token";

        public String updateAccessToken(final Map<String, Object> params) {
            return new SQL() {
                {
                    UPDATE(TABLE_NAME);

                    if (params.get("accessToken") != null) {
                        SET("access_token = #{accessToken}");
                    }

                    WHERE("wechat_account = #{wechatAccount}");
                }

            }.toString();
        }

        public String save(final WeChatAccessToken weChatAccessToken) {
            return new SQL() {
                {
                    INSERT_INTO(TABLE_NAME);

                    if (!Strings.isNullOrEmpty(weChatAccessToken.getWechatAccount())) {
                        VALUES("wechat_account", "#{wechatAccount}");
                    }

                    if (!Strings.isNullOrEmpty(weChatAccessToken.getAccessToken())) {
                        VALUES("access_token", "#{accessToken}");
                    }

                    if (weChatAccessToken.getCreateTime() != null) {
                        VALUES("create_time", "#{create_time}");
                    }

                    if (weChatAccessToken.getLastModifiedTime() != null) {
                        VALUES("last_modified_time", "#{lastModifiedTime}");
                    }
                }
            }.toString();
        }

        public String queryByAccount(final Map<String, Object> params) {
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
