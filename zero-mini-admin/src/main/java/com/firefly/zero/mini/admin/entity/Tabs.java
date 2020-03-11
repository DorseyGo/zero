/**
 * File: Tabs
 * Author: DORSEy Q F TANG
 * Created: 2020/3/11
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class Tabs implements Entity {

    private short id;
    private String name;
    private String leadingImgUrl;
    private String creator;
    /**
     * Activate when initialize
     * Flag, which indicates require an extra active class when first initialize.
     */
    @JsonProperty("active")
    private short activateWhenInit;

    private Date createTime;
    private Date lastModifiedTime;

    @Tolerate
    public Tabs() {}
}
