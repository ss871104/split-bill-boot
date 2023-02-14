package com.menstalk.notificationservice.service;

import java.util.List;

import com.menstalk.notificationservice.domain.NotificationVo;

public interface NotificationService {
	
	List<NotificationVo> notificationVo();
	boolean addNotification(NotificationVo notification);
	
	
}
