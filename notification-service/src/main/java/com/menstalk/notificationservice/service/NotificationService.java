package com.menstalk.notificationservice.service;

import com.menstalk.notificationservice.domain.Notification;
import com.menstalk.notificationservice.dto.*;

public interface NotificationService {
    boolean addNewUserNotification(NewUserRequest newUserRequest);
    boolean addNewBillNotification(NewBillRequest newBillRequest);
    boolean addNewMemberNotification(NewMemberRequest newMemberRequest);
    NotificationResponse findByUserId(Long userId);
    boolean updateStatus(ReadRequest readRequest);

}
