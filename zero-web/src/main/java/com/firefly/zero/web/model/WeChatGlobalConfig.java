/**
 * File: WeChatGlobalConfig
 * Author: DorSey Q F TANG
 * Created: 2020/2/9 10:43
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.model;

import lombok.*;

import java.util.Date;

/**
 * The container, which contains the global configuration for <code>WeChat</code>
 */
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class WeChatGlobalConfig {

    private int id;
    private String appId;
    private String appSecret;
    private String encodingAESKey;
    private String token;
    private Date createTime;

}

