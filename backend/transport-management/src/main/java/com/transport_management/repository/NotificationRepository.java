package com.transport_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transport_management.entity.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUserUserIdAndDeletedAtIsNull(Long userId);

    List<Notification> findAllByUserUserIdAndIsReadFalseAndDeletedAtIsNull(Long userId);

    List<Notification> findAllByDeletedAtIsNull();
}