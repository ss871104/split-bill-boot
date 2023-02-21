package com.menstalk.notificationservice.service;

import java.util.List;

import com.menstalk.notificationservice.domain.NotificationVo;
import com.menstalk.notificationservice.dto.NotificationRequest;

public interface NotificationService {

	List<NotificationVo> notificationVo();

	boolean addInvitationNotification(NotificationRequest notificationRequest);

	List<NotificationVo> notificationjoin();

	boolean addJoinNotification(NotificationRequest notificationRequest);

	List<NotificationVo> notificationnewuser();

	boolean addNewuserNotification(NotificationRequest notificationRequest);

	List<NotificationVo> notificationbilladd();

	boolean addbilladdNotification(NotificationRequest notificationRequest);

}
