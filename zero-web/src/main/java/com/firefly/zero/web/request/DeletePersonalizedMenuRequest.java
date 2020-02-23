/**
 * File: DeletePersonalizedMenuRequest
 * Author: DorSey Q F TANG
 * Created: 2020/2/21 10:28
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.request;

import lombok.*;

@Data
@Builder
public class DeletePersonalizedMenuRequest implements Request {
    private String menuId;
}
