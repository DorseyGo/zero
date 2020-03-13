/**
 * File: NotificationServiceImpl
 * Author: DorSey Q F TANG
 * Created: 2020/3/13
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.service.impl;

import com.firefly.zero.mini.admin.conf.MiniAdminConfig;
import com.firefly.zero.mini.admin.entity.Notification;
import com.firefly.zero.mini.admin.repository.NotificationRepository;
import com.firefly.zero.mini.admin.service.NotificationService;
import com.firefly.zero.mini.admin.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final MiniAdminConfig config;

    @Autowired
    public NotificationServiceImpl(@Qualifier("notificationRepository") final NotificationRepository notificationRepository,
                                   final MiniAdminConfig config) {
        this.notificationRepository = notificationRepository;
        this.config = config;
    }

    @Override
    public List<Notification> getNotifications(boolean enable) {
        final List<Notification> notifications = notificationRepository.queryForList((enable ? 0 : 1));
        notifications.forEach(notification -> notification.setSlide(Utils.concat(config.getImageServerUrl(), notification.getSlide())));

        return notifications;
    }
}
