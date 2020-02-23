/**
 * File: BasicController
 * Author: DorSey Q F TANG
 * Created: 2020/2/21 15:43
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.controller;

import com.firefly.zero.web.model.Constants;

public class BasicController {

    protected String appendWildCardIfPossible(final String name) {
        return (name == null) ? null :
                (name.endsWith(Constants.PERCENTAGE) ? name : (name + Constants.PERCENTAGE));
    }

}
