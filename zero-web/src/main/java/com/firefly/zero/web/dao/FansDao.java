/**
 * File: FansDao
 * Author: DorSey Q F TANG
 * Created: 2020/2/19 14:22
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.dao;

import com.firefly.zero.web.model.Fans;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Repository("fansDao")
public interface FansDao {

    @ResultType(Integer.class)
    @InsertProvider(type = FansSQLProvider.class, method = "batchSave")
    int batchSave(@Param("fans") final List<Fans> fans);

    @ResultType(Integer.class)
    @SelectProvider(type = FansSQLProvider.class, method = "count")
    int count(@Param("groupId") Long groupId, @Param("city") String city, @Param("country") String country,
            @Param("province") String province);

    @Results(id = "fansRM", value = {
            @Result(column = "open_id", property = "openId", jdbcType = JdbcType.VARCHAR),
            @Result(column = "subscribe", property = "subscribe", jdbcType = JdbcType.TINYINT),
            @Result(column = "nick_name", property = "nickName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "gender", property = "gender", jdbcType = JdbcType.TINYINT),
            @Result(column = "city", property = "city", jdbcType = JdbcType.VARCHAR),
            @Result(column = "group_names", property = "groupNames", jdbcType = JdbcType.VARCHAR),
            @Result(column = "country", property = "country", jdbcType = JdbcType.VARCHAR),
            @Result(column = "province", property = "province", jdbcType = JdbcType.VARCHAR),
            @Result(column = "heading_img_url", property = "headingImgUrl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "subscribe_time", property = "subscribeTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "remark", property = "remark", jdbcType = JdbcType.VARCHAR)
    })
    @SelectProvider(type = FansSQLProvider.class, method = "queryForSubList")
    List<Fans> queryForSubList(@Param("groupId") Long groupId, @Param("city") String city,
            @Param("country") String country, @Param("province") String province, @Param("offset") int offset,
            @Param("limit") int limit);

    @UpdateProvider(type = FansSQLProvider.class, method = "updateRemarkByOpenId")
    void updateRemarkByOpenId(@Param("openId") final String openId, @Param("remark") final String remark);


    // ~~~ SQL provider
    // ----------------------------------------------------
    class FansSQLProvider {
        static final String TABLE_NAME = "fans";
        static final String TABLE_GROUP = "`group`";
        static final String TABLE_FANS_GROUP = "fans_group";
        static final String COLUMNS = "open_id, subscribe, nick_name, gender, city, country, province, " +
                "language, heading_img_url, subscribe_time, union_id, remark, subscribe_scene, " +
                "qr_scene, qr_scene_desc";
        static final String MESSAGE_FORMAT_4_INSERT = "(#'{'fans[{0}].openId}, #'{'fans[{0}].subscribe}," +
                "#'{'fans[{0}].nickName}, #'{'fans[{0}].gender}, #'{'fans[{0}].city}, " +
                "#'{'fans[{0}].country}, #'{'fans[{0}].province}, #'{'fans[{0}].language}, #'{'fans[{0}].headingImgUrl}, " +
                "#'{'fans[{0}].subscribeTime}, #'{'fans[{0}].unionId}, #'{'fans[{0}].remark}," +
                "#'{'fans[{0}].subscribeScene}, #'{'fans[{0}].qrScene}, #'{'fans[{0}].qrSceneDesc})";

        public String batchSave(final Map<String, Object> params) {
            final StringBuilder builder = new StringBuilder();
            builder.append("INSERT INTO " + TABLE_NAME);
            builder.append("(" + COLUMNS + ") VALUES ");
            final MessageFormat format = new MessageFormat(MESSAGE_FORMAT_4_INSERT);

            final List<Fans> fans = (List<Fans>) params.get("fans");
            for (int index = 0; index < fans.size(); index++) {
                if (index > 0) {
                    builder.append(", ");
                }

                builder.append(format.format(new Object[]{index}));
            }

            return builder.toString();
        }

        public String count(final Map<String, Object> params) {
            return new SQL() {
                {
                    SELECT("COUNT(*)");
                    FROM(TABLE_NAME + " AS fans");

                    if (params.get("groupId") != null) {
                        INNER_JOIN(
                                TABLE_FANS_GROUP + " AS fg ON fg.open_id = fans.open_id AND fg.group_id = #{groupId}");
                    }


                    if (params.get("city") != null) {
                        WHERE("city = #{city}");
                    }

                    if (params.get("country") != null) {
                        WHERE("country = #{country}");
                    }

                    if (params.get("province") != null) {
                        WHERE("province = #{province}");
                    }
                }
            }.toString();
        }

        public String queryForSubList(final Map<String, Object> params) {
            return new SQL() {
                {
                    SELECT("f.open_id, f.subscribe, f.nick_name, f.gender, f.city, f.country, " +
                            "f.province, f.heading_img_url, f.subscribe_time, f.remark, GROUP_CONCAT(g.name) AS group_names");
                    FROM(TABLE_NAME + " AS f", TABLE_GROUP + " AS g", TABLE_FANS_GROUP + " AS fg");
                    WHERE("f.open_id = fg.open_id AND fg.group_id = g.id");

                    if (params.get("groupId") != null) {
                        WHERE("g.id = #{groupId}");
                    }

                    if (params.get("city") != null) {
                        WHERE("f.city = #{city}");
                    }

                    if (params.get("country") != null) {
                        WHERE("f.country = #{country}");
                    }

                    if (params.get("province") != null) {
                        WHERE("f.province = #{province}");
                    }

                    GROUP_BY("f.open_id");
                }
            }.toString();
        }

        public String updateRemarkByOpenId(final Map<String, Object> params) {
            return new SQL() {
                {
                    UPDATE(TABLE_NAME);
                    SET("remark = #{remark}");
                    WHERE("open_id = #{openId}");
                }
            }.toString();
        }
    }
}
