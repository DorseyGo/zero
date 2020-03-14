/**
 * File: GreatMomentServiceImpl
 * Author: DorSey Q F TANG
 * Created: 2020/3/14 10:14
 * CopyRight: All rights reserved
 */
package com.firefly.zero.mini.admin.service.impl;

import com.firefly.zero.mini.admin.entity.GreatMoment;
import com.firefly.zero.mini.admin.repository.GreatMomentRepository;
import com.firefly.zero.mini.admin.service.GreatMomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("greatMomentService")
public class GreatMomentServiceImpl implements GreatMomentService {

    private final GreatMomentRepository greatMomentRepository;

    @Autowired
    public GreatMomentServiceImpl(@Qualifier("greatMomentRepository") final GreatMomentRepository greatMomentRepository) {
        this.greatMomentRepository = greatMomentRepository;
    }

    @Override
    public List<GreatMoment> getAllGreatMoments() {
        return greatMomentRepository.queryForList();
    }
}
