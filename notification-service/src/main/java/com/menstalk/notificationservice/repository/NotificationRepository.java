package com.menstalk.notificationservice.repository;

import com.menstalk.notificationservice.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {  // 結尾加上extends(繼承) Jpar 幫你實作方法

	List<Notification> findByUserId(Long userId);

}
