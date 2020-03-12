/**
 * File: CourseCategory
 * Author: DorSey Q F TANG
 * Created: 2020/3/12
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class CourseCategory implements Entity {
    private short id;
    private String category;

    @Tolerate
    public CourseCategory() {}
}
