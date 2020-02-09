/**
 * File: WeChatAccessTokenDao
 * Author: DorSey Q F TANG
 * Created: 2020/2/9 11:21
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.dao;

import com.firefly.zero.web.model.WeChatAccessToken;
import com.google.common.base.Strings;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("weChatAccessTokenDao")
public interface WeChatAccessTokenDao {

    @InsertProvider(type = WeChatAccessTokenSqlProvider.class, method = "save")
    void save(@Param("wechatAccessToken") final WeChatAccessToken weChatAccessToken);

    @UpdateProvider(type = WeChatAccessTokenSqlProvider.class, method = "updateAccessToken")
    void updateAccessToken(@Param("uuid") final String uuid, @Param("accessToken") final String accessToken);

    @SelectProvider(type = WeChatAccessTokenSqlProvider.class, method = "queryForList")
    List<WeChatAccessToken> queryForList();


    class WeChatAccessTokenSqlProvider {
        private static final String TABLE_NAME = "wechat_access_token";

        public String updateAccessToken(final String uuid, final String accessToken) {
            return new SQL() {
                {
                    UPDATE(TABLE_NAME);

                    if (!Strings.isNullOrEmpty(accessToken)) {
                        SET("access_token = #{accessToken}");
                    }

                    WHERE("uuid = #{uuid}");
                }

            }.toString();
        }

        public String save(final WeChatAccessToken weChatAccessToken) {
            return new SQL() {
                {
                    INSERT_INTO(TABLE_NAME);

                    if (!Strings.isNullOrEmpty(weChatAccessToken.getUuid())) {
                        VALUES("uuid", "#uuid");
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

        public String queryForList() {
            return new SQL() {
                {
                    SELECT("*");

                    FROM(TABLE_NAME);
                }
            }.toString();
        }
    }
}
