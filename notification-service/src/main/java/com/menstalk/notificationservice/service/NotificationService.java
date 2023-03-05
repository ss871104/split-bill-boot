package com.menstalk.notificationservice.service;

import com.menstalk.notificationservice.dto.NewBillRequest;
import com.menstalk.notificationservice.dto.NewMemberRequest;
import com.menstalk.notificationservice.dto.NewUserRequest;
import com.menstalk.notificationservice.dto.NotificationResponse;

import java.util.List;

public interface NotificationService {
    boolean addNewUserNotification(NewUserRequest newUserRequest);
    boolean addNewBillNotification(NewBillRequest newBillRequest);
    boolean addNewMemberNotification(NewMemberRequest newMemberRequest);
    List<NotificationResponse> findByUserId(Long userId);
    boolean updateStatus(Long notificationId);
    List<NotificationResponse> getAllNotifications();

}
