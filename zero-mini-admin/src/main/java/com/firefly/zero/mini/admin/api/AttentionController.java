/**
 * File: AttentionController
 * Author: DorSey Q F TANG
 * Created: 2020/3/14 10:36
 * CopyRight: All rights reserved
 */
package com.firefly.zero.mini.admin.api;

import com.firefly.zero.mini.admin.entity.Attention;
import com.firefly.zero.mini.admin.response.Response;
import com.firefly.zero.mini.admin.service.AttentionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Controller // + @ResponseBody
@RequestMapping("/api")
public class AttentionController {

    private final AttentionService attentionService;

    public AttentionController(@Qualifier("attentionService") final AttentionService attentionService) {
        this.attentionService = attentionService;
    }

    @GetMapping("/attentions/{name}")
    public Response<Attention> getAttentions(@PathVariable("name") final String name) {
        final List<Attention> attentions = attentionService.getAllAttentions();

        return new Response<Attention>().status(Response.SUCCESS).data(attentions);
    }

}
