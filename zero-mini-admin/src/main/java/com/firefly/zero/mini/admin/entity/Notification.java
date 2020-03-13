/**
 * File: Notification
 * Author: DorSey Q F TANG
 * Created: 2020/3/13
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

@Data
@Builder
public class Notification implements Entity {
    private int id;
    private String slide;

    private short enable;
    private String creator;
    private Date createTime;
    private Date lastModifiedTime;

    @Tolerate
    public Notification() {}
}
