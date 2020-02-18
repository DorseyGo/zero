/**
 * File: GroupServiceImpl
 * Author: DorSey Q F TANG
 * Created: 2020/2/14 15:02
 * CopyRight: All rights reserved
 */
package com.firefly.zero.web.service.impl;

import com.firefly.zero.web.api.WeChatGroupApi;
import com.firefly.zero.web.dao.GroupDao;
import com.firefly.zero.web.exception.ApiInvocationException;
import com.firefly.zero.web.model.Constants;
import com.firefly.zero.web.model.Group;
import com.firefly.zero.web.model.Pagination;
import com.firefly.zero.web.request.CreateGroupRequest;
import com.firefly.zero.web.request.DeleteGroupRequest;
import com.firefly.zero.web.request.UpdateGroupRequest;
import com.firefly.zero.web.service.GroupService;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import me.chanjar.weixin.mp.bean.WxMpGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service("groupService")
public class GroupServiceImpl implements GroupService {

    static Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);
    static final List<Long> UNREMOVABLE_TAG_IDS = Lists.newArrayList(0L, 1L, 2L);
    private final GroupDao groupDao;
    private final WeChatGroupApi weChatGroupApi;

    @Autowired
    public GroupServiceImpl(@Qualifier("groupDao") final GroupDao groupDao,
            @Qualifier("weChatGroupApi") final WeChatGroupApi weChatGroupApi) {
        this.groupDao = groupDao;
        this.weChatGroupApi = weChatGroupApi;
    }

    @Override
    public Group getGroupById(final long id) {
        return groupDao.queryById(id);
    }

    @Override
    public void deleteGroupById(final long id) throws ApiInvocationException {
        Preconditions.checkArgument(!UNREMOVABLE_TAG_IDS.contains(id), "The tag id[" + id + "] can't be removed");

        if (!weChatGroupApi.getConfig().isDebug()) {
            weChatGroupApi.delete(new DeleteGroupRequest(id));
        }

        groupDao.deleteGroupById(id);
    }

    @Override
    public void updateGroup(final long id, final String name) throws ApiInvocationException {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "Group name MUST not be null or empty");

        if (!weChatGroupApi.getConfig().isDebug())
            weChatGroupApi.update(new UpdateGroupRequest(id, name));

        groupDao.updateGroupById(id, name);
    }

    @Override
    public void saveGroup(final String name) throws ApiInvocationException {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "Group name MUST not be null or empty");

        Group group = null;
        if (!weChatGroupApi.getConfig().isDebug()) {
            WxMpGroup wxMpGroup = weChatGroupApi.create(new CreateGroupRequest(name));
            group = new Group(wxMpGroup.getId(), wxMpGroup.getName(), wxMpGroup.getCount());
        }

        groupDao.saveGroup(new Group(0L, name, 0L));
    }

    @Override
    public Pagination<Group> paginate(String name, Date startTime, Date endTime, int offset, int pageSize) {
        name = appendWildCardIfPossible(name);
        final int totalRecords = groupDao.count(name, startTime, endTime);
        if (totalRecords == 0) {
            return new Pagination<>();
        }

        final int curPage = (offset / pageSize) + 1;
        final List<Group> groups = groupDao.queryForSubList(name, startTime, endTime, offset, pageSize);
        return new Pagination<>(curPage, pageSize, totalRecords, groups);
    }

    private String appendWildCardIfPossible(String name) {
        return (name == null) ? null :
                (name.endsWith(Constants.PERCENTAGE) ? name : (name + Constants.PERCENTAGE));
    }

}
