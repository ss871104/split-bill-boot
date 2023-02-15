package com.menstalk.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.menstalk.notificationservice.domain.NotificationVo;

public interface NotificationRepository extends JpaRepository<NotificationVo, Long> {  // 結尾加上extends(繼承) Jpar 幫你實作方法

}