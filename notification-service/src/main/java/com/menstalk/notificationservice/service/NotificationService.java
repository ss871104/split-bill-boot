package com.menstalk.notificationservice.service;

import java.util.List;

import com.menstalk.notificationservice.domain.Notification;
import com.menstalk.notificationservice.dto.NotificationRequest;

public interface NotificationService {

	List<Notification> notificationVo();

	boolean addInvitationNotification(NotificationRequest notificationRequest);

	List<Notification> notificationjoin();

	boolean addJoinNotification(NotificationRequest notificationRequest);

	List<Notification> notificationnewuser();

	boolean addNewuserNotification(NotificationRequest notificationRequest);

	List<Notification> notificationbilladd();

	boolean addbilladdNotification(NotificationRequest notificationRequest);

}
