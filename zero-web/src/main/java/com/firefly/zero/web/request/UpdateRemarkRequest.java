/**
 * File: UpdateRemarkRequest
 * Author: DorSey Q F TANG
 * Created: 2020/2/20 09:50
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class UpdateRemarkRequest implements Request {
    private final String openId;
    private final String remark;
}
