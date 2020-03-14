/**
 * File: Attention
 * Author: DorSey Q F TANG
 * Created: 2020/3/14 11:03
 * CopyRight: All rights reserved
 */
package com.firefly.zero.mini.admin.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class Attention implements Entity {
    private short id;
    private String title;

    private String creator;
    private Date lastModifiedTime;

    @Tolerate
    public Attention() {}
}
