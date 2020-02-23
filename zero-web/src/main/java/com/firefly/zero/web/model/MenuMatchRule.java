/**
 * File: MenuMatchRule
 * Author: DorSey Q F TANG
 * Created: 2020/2/21 10:13
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.model;

import lombok.*;

@Data
@Builder
public class MenuMatchRule {

    private Long groupId;
    private int gender;
    private String country;
    private String province;
    private String city;
    private int platformType;
    private String language;

}
