/**
 * File: NotificationController
 * Author: DorSey Q F TANG
 * Created: 2020/3/13
 * CopyRight: All rights reserved
 **/
package com.firefly.zero.mini.admin.api;

import com.firefly.zero.mini.admin.entity.Notification;
import com.firefly.zero.mini.admin.response.Response;
import com.firefly.zero.mini.admin.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(@Qualifier("notificationService") final NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications")
    public Response<Notification> getAvailableNotifications() {
        final List<Notification> notifications = notificationService.getNotifications(true);

        return new Response<Notification>().status(Response.SUCCESS).data(notifications);
    }
}
