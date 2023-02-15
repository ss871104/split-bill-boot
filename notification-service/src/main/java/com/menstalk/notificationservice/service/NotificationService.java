package com.menstalk.notificationservice.service;

import java.util.List;

import com.menstalk.notificationservice.domain.NotificationVo;
import com.menstalk.notificationservice.dto.NotificationRequest;

public interface NotificationService {
	
	List<NotificationVo> notificationVo();
	boolean addInvitationNotification(NotificationRequest notificationRequest);
	
	
}
