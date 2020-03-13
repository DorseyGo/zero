/**
 * File: NotificationService
 * Author: DorSey Q F TANG
 * Created: 2020/3/13
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.service;

import com.firefly.zero.mini.admin.entity.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotifications(final boolean enable);
}
