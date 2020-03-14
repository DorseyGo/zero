/**
 * File: GreatMoment
 * Author: DorSey Q F TANG
 * Created: 2020/3/14 10:13
 * CopyRight: All rights reserved
 */
package com.firefly.zero.mini.admin.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class GreatMoment implements Entity {

    private short id;
    private String thumbnail_path;
    private String brief;
    private String creator;
    private Date lastModifiedTime;

    @Tolerate
    public GreatMoment() {}
}
