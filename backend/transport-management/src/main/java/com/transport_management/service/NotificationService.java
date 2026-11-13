package com.transport_management.service;

import com.transport_management.entity.Notification;

import java.util.List;

public interface NotificationService {

    List<Notification> getUserNotifications(Long userId);

    List<Notification> getUnreadNotifications(Long userId);

    Notification markAsRead(Long notificationId);

    void markAllAsRead(Long userId);

    Notification sendNotification(Notification notification);
}