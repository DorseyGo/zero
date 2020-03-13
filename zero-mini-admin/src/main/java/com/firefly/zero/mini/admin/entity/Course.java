/**
 * File: Course
 * Author: DorSey Q F TANG
 * Created: 2020/3/13
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Course implements Entity {
    private short id;
    private String name;
    private String brief;
    private String imageUrl;
}
