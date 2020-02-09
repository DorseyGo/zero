/**
 * File: WeChatAccessToken
 * Author: DorSey Q F TANG
 * Created: 2020/2/9 11:17
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.model;

import lombok.*;

import java.util.Date;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class WeChatAccessToken {

    @NonNull
    private String uuid;

    @NonNull
    private String accessToken;
    private Date createTime;
    private Date lastModifiedTime;

}
