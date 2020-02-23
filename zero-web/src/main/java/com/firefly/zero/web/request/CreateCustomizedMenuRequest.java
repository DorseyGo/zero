/**
 * File: CreateCustomizedMenuRequest
 * Author: DorSey Q F TANG
 * Created: 2020/2/21 10:03
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.request;

import com.firefly.zero.web.struct.Menu;
import com.firefly.zero.web.model.MenuMatchRule;
import com.google.common.collect.Lists;
import lombok.*;

import java.util.List;

@Data
@Builder
public class CreateCustomizedMenuRequest implements Request {

    @Builder.Default
    private List<Menu> menus = Lists.newArrayList();

    private MenuMatchRule matchRule;

}
