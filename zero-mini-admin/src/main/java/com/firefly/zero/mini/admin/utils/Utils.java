/**
 * File: Utils
 * Author: DorSey Q F TANG
 * Created: 2020/3/12
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.utils;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class Utils {
    public static String concat(final String prefix, final String content) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(prefix), "prefix Required");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(content), "content Required");

        return prefix.replaceAll("/$", "") + "/" + content.replaceAll("^/", "");
    }

}
