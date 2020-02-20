/**
 * File: FansServiceImpl
 * Author: DorSey Q F TANG
 * Created: 2020/2/19 14:21
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service.impl;

import com.firefly.zero.web.api.WeChatUserApi;
import com.firefly.zero.web.dao.FansDao;
import com.firefly.zero.web.exception.ApiInvocationException;
import com.firefly.zero.web.model.Fans;
import com.firefly.zero.web.model.Pagination;
import com.firefly.zero.web.request.UpdateRemarkRequest;
import com.firefly.zero.web.service.FansService;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("fansService")
public class FansServiceImpl implements FansService {

    static final Logger logger = LoggerFactory.getLogger(FansServiceImpl.class);
    private final FansDao fansDao;
    private final WeChatUserApi weChatUserApi;

    @Autowired
    public FansServiceImpl(@Qualifier("fansDao") final FansDao fansDao,
            @Qualifier("weChatUserApi") final WeChatUserApi weChatUserApi) {
        this.fansDao = fansDao;
        this.weChatUserApi = weChatUserApi;
    }

    @Override
    public boolean batchSave(final List<Fans> fans) {
        if (fans.isEmpty()) {
            return true;
        }

        return (fansDao.batchSave(fans) > 0);
    }

    @Override
    public Pagination<Fans> paginate(Long groupId, String city, String country,
            String province, final int offset, final int pageSize) {
        final int totalRecords = fansDao.count(groupId, city, country, province);
        if (totalRecords == 0) {
            logger.info("No records found according to groupId {}, city {}, country {}, province {}", groupId, city,
                    country, province);
            return new Pagination<>();
        }

        final int curPage = (offset / pageSize) + 1;
        final List<Fans> fans = fansDao.queryForSubList(groupId, city, country, province, offset, pageSize);
        return new Pagination<>(curPage, pageSize, totalRecords, fans);
    }

    @Override
    @Transactional(noRollbackFor = ApiInvocationException.class, propagation = Propagation.REQUIRED)
    public void updateRemark(final String openId, final String remark) throws ApiInvocationException {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(openId), "Open ID not specified");
        UpdateRemarkRequest request = new UpdateRemarkRequest(openId, remark);
        weChatUserApi.update(request);

        fansDao.updateRemarkByOpenId(openId, remark);
    }
}
