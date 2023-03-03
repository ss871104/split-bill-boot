package com.menstalk.notificationservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.menstalk.notificationservice.domain.Notification;
import com.menstalk.notificationservice.domain.NotificationStatus;
import com.menstalk.notificationservice.domain.NotificationType;
import com.menstalk.notificationservice.dto.NewBillRequest;
import com.menstalk.notificationservice.dto.NewMemberRequest;
import com.menstalk.notificationservice.dto.NewUserRequest;
import com.menstalk.notificationservice.dto.NotificationResponse;
import com.menstalk.notificationservice.dto.ReadRequest;
import com.menstalk.notificationservice.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements NotificationService{
	
	private final NotificationRepository notificationRepository; //依賴注入中的建構式注入。
    
	
	@Override
	public boolean addNewUserNotification(NewUserRequest newUserRequest) {
		try {
			Notification notification = Notification.builder()
					.userId(newUserRequest.getUserId())
					.title(NotificationType.NEW_USER.getTitle())
					.content(NotificationType.NEW_USER.getContent().formatted(newUserRequest.getUserName()))
					.status(NotificationStatus.UNREAD)
					.createTime(LocalDateTime.now())
					.build();
				notificationRepository.save(notification);
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
}
	

	@Override
	public boolean addNewBillNotification(NewBillRequest newBillRequest) {
		try {
		Notification notification = Notification.builder()
				.userId(newBillRequest.getPartyId())
				.title(NotificationType.BILL_ADD.getTitle())
				.content(NotificationType.BILL_ADD.getContent().formatted(newBillRequest.getPartyName()))
				.status(NotificationStatus.UNREAD)
				.createTime(LocalDateTime.now())
				.build();
			notificationRepository.save(notification);
			return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
		}
	

	@Override
	public boolean addNewMemberNotification(NewMemberRequest newMemberRequest) {
	
			try {
			Notification notification = Notification.builder()
					.userId(newMemberRequest.getPartyId())
					.title(NotificationType.JOIN.getTitle())
					.content(NotificationType.JOIN.getContent().formatted(newMemberRequest.getMemberName()))
					.status(NotificationStatus.UNREAD)
					.createTime(LocalDateTime.now())
					.build();
				notificationRepository.save(notification);
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		}

	@Override
	public List<NotificationResponse> findByUserId(Long userId) {
		try {
			List<Notification> notificationList = notificationRepository.findByUserId(userId);
			
			List<NotificationResponse> inviteNotificationList = notificationList.stream()
						.filter(x -> x.getMemberId() != null)
						.map(x -> 
								NotificationResponse.builder()
									.notificationId(x.getNotificationId())
									.userId(x.getUserId())
									.title(x.getTitle())
									.content(x.getContent())
									.createTime(x.getCreateTime())
									.status(x.getStatus())
									.memberId(x.getMemberId())
									.build()
						)
						.collect(Collectors.toList());
			
			List<NotificationResponse> otherNotificationList = notificationList.stream()
					.filter(x -> x.getMemberId() == null)
					.map(x -> 
							NotificationResponse.builder()
								.notificationId(x.getNotificationId())
								.userId(x.getUserId())
								.title(x.getTitle())
								.content(x.getContent())
								.createTime(x.getCreateTime())
								.status(x.getStatus())
								.build()
					)
					.collect(Collectors.toList());
			
			List<NotificationResponse> mergeNotificationList = new ArrayList<>();
			
			mergeNotificationList.addAll(inviteNotificationList);
			mergeNotificationList.addAll(otherNotificationList);
			
			mergeNotificationList.sort(Comparator.comparing(NotificationResponse::getNotificationId));
			
			return mergeNotificationList;
		
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateStatus(ReadRequest readRequest) {
		// TODO Auto-generated method stub
		return false;
	}

}
