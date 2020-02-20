/**
 * File: Fans
 * Author: DorSey Q F TANG
 * Created: 2020/2/19 12:06
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.model;


import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * The entity for fans.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Fans {
    private String openId;
    private int subscribe;
    private String nickName;

    /**
     * 0, for unknown
     * 1, for male,
     * 2, for female
     */
    private int gender;
    private String city;
    private String country;
    private String province;
    private String language;
    private String headingImgUrl;
    private Date subscribeTime;
    private String unionId;
    private String remark;

    private String subscribeScene;
    private Long qrScene;
    private String qrSceneDesc;


    // ~~~ group related
    private List<Integer> tagId;
    private String groupNames;
}
