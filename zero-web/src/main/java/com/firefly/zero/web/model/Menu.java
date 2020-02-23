/**
 * File: Menu
 * Author: DorSey Q F TANG
 * Created: 2020/2/21 15:04
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

/**
 * The entity for mapping to the underlying table.
 */
@Data
@Builder
public class Menu {

    private int id;
    private String type;
    private String name;
    private String key;
    private String url;

    @JsonFormat(pattern = Constants.DEFAULT_DATE_PATTERN, timezone = Constants.DEFAULT_TIMEZONE)
    @JsonProperty("create_time")
    private Date createTime;

}
