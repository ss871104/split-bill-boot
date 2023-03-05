package com.menstalk.notificationservice.service;

import com.menstalk.notificationservice.domain.Notification;
import com.menstalk.notificationservice.domain.NotificationStatus;
import com.menstalk.notificationservice.domain.NotificationType;
import com.menstalk.notificationservice.dto.NewBillRequest;
import com.menstalk.notificationservice.dto.NewMemberRequest;
import com.menstalk.notificationservice.dto.NewUserRequest;
import com.menstalk.notificationservice.dto.NotificationResponse;
import com.menstalk.notificationservice.mapper.NotificationMapper;
import com.menstalk.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements NotificationService{
	
	private final NotificationRepository notificationRepository; //依賴注入中的建構式注入。
	private final NotificationMapper notificationMapper;
    
	
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
			List<Notification> notificationList = newBillRequest.getUseridList().stream()
					.map(x -> Notification.builder()
							.userId(x)
							.title(NotificationType.BILL_ADD.getTitle())
							.content(NotificationType.BILL_ADD.getContent().formatted(newBillRequest.getPartyName()))
							.status(NotificationStatus.UNREAD)
							.createTime(LocalDateTime.now())
							.build()
					)
					.collect(Collectors.toList());

			notificationRepository.saveAll(notificationList);
			return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
		}
	

	@Override
	public boolean addNewMemberNotification(NewMemberRequest newMemberRequest) {
	
			try {
				List<Notification> notificationList = newMemberRequest.getUserIds().stream()
						.map(x -> Notification.builder()
								.userId(x)
								.title(NotificationType.JOIN.getTitle())
								.content(NotificationType.JOIN.getContent().formatted(newMemberRequest.getMemberName()))
								.status(NotificationStatus.UNREAD)
								.createTime(LocalDateTime.now())
								.build()
						)
						.collect(Collectors.toList());

				notificationRepository.saveAll(notificationList);
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

			List<NotificationResponse> result = notificationList.stream()
					.map(notificationMapper::notificationToDto)
					.collect(Collectors.toList());

			return result;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean updateStatus(Long notificationId) {
		try {
			Notification notification = notificationRepository.findById(notificationId).orElseThrow();
			notification.setStatus(NotificationStatus.READ);
			notificationRepository.save(notification);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
