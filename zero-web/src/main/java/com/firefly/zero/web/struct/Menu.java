/**
 * File: Menu
 * Author: DorSey Q F TANG
 * Created: 2020/2/21 10:11
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.struct;

import com.google.common.collect.Lists;
import lombok.*;

import java.util.List;

@Data
@Builder
public class Menu {

    private String type;
    private String name;
    private String key;
    private String url;

    @Builder.Default
    private List<Menu> subButtons = Lists.newArrayList();

}
