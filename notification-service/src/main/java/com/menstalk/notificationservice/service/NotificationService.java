package com.menstalk.notificationservice.service;

import java.util.List;

import com.menstalk.notificationservice.dto.*;

public interface NotificationService {
    boolean addNewUserNotification(NewUserRequest newUserRequest);
    boolean addNewBillNotification(NewBillRequest newBillRequest);
    boolean addNewMemberNotification(NewMemberRequest newMemberRequest);
    List<NotificationResponse> findByUserId(Long userId);
    boolean updateStatus(ReadRequest readRequest);

}
