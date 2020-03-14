/**
 * File: AttentionServiceImpl
 * Author: DorSey Q F TANG
 * Created: 2020/3/14 10:48
 * CopyRight: All rights reserved
 */
package com.firefly.zero.mini.admin.service.impl;

import com.firefly.zero.mini.admin.entity.Attention;
import com.firefly.zero.mini.admin.repository.AttentionRepository;
import com.firefly.zero.mini.admin.service.AttentionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("attentionService")
public class AttentionServiceImpl implements AttentionService {

    private final AttentionRepository attentionRepository;

    public AttentionServiceImpl(@Qualifier("attentionRepository") final AttentionRepository attentionRepository) {
        this.attentionRepository = attentionRepository;
    }

    @Override
    public List<Attention> getAllAttentions() {
        return attentionRepository.queryForList();
    }
}
