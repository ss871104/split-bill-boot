package com.menstalk.notificationservice.service;

import java.time.LocalDateTime;
import java.util.List;

import com.menstalk.notificationservice.domain.NotificationStatus;
import com.menstalk.notificationservice.domain.NotificationType;
import com.menstalk.notificationservice.dto.NotificationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.menstalk.notificationservice.domain.NotificationVo;
import com.menstalk.notificationservice.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service // 讓 spring 知道這個 class 是要交由 spring 去管理的，並且是 Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepository; // 呼叫DAo(repository)

	@Override
	public boolean addInvitationNotification(NotificationRequest notificationRequest) {
		// 現在要寫讓通知進到DAO再進到資料庫!!!
		// NotificationVo newNotificationVo = notificationRepository.save(notification);
		try {
			// 需要透過 partyId 查 partyName，現在先寫死
			NotificationVo notification = NotificationVo.builder()
					.userId(notificationRequest.getUserIdsReceiveNotification().get(0))
					.title(NotificationType.INVITE.getTitle())
					.content(NotificationType.INVITE.getContent().formatted("猴子俱樂部")).status(NotificationStatus.UNREAD)
					.createTime(LocalDateTime.now()).build();
			notificationRepository.save(notification);
			return true;
		} catch (Exception e) {
			e.printStackTrace(); // 也可用 System.out.println(e) ，執行後若有錯誤印出在CONSOLE，但e.printStackTrace() 可印出較詳細的錯誤。
			return false;
		}

	}

	@Override
	public List<NotificationVo> notificationVo() {
		return notificationRepository.findAll();
//		return null;

	}

	@Override
	public boolean addJoinNotification(NotificationRequest notificationRequest) {
		// 現在要寫讓通知進到DAO再進到資料庫!!!
		// NotificationVo newNotificationVo = notificationRepository.save(notification);
		try {
			// 需要透過 userId 查 userName，現在先寫死
			NotificationVo notification = NotificationVo.builder()
					.userId(notificationRequest.getUserIdsReceiveNotification().get(0))
					.title(NotificationType.JOIN.getTitle())
					.content(NotificationType.JOIN.getContent().formatted("userId")).status(NotificationStatus.UNREAD)// 預設狀態是未讀
					.createTime(LocalDateTime.now()).build();
			notificationRepository.save(notification);
			return true;
		} catch (Exception e) {
			e.printStackTrace(); // 也可用 System.out.println(e) ，執行後若有錯誤印出在CONSOLE，但e.printStackTrace() 可印出較詳細的錯誤。
			return false;
		}

	}

	@Override
	public boolean addNewuserNotification(NotificationRequest notificationRequest) {
		// 現在要寫讓通知進到DAO再進到資料庫!!!
		// NotificationVo newNotificationVo = notificationRepository.save(notification);
		try {
			// 需要透過 UserId 查 userName，現在先寫死
			NotificationVo notification = NotificationVo.builder()
					.userId(notificationRequest.getUserIdsReceiveNotification().get(0))
					.title(NotificationType.NEW_USER.getTitle())
					.content(NotificationType.NEW_USER.getContent().formatted("userId"))
					.status(NotificationStatus.UNREAD)// 預設狀態是未讀
					.createTime(LocalDateTime.now()).build();
			notificationRepository.save(notification);
			return true;
		} catch (Exception e) {
			e.printStackTrace(); // 也可用 System.out.println(e) ，執行後若有錯誤印出在CONSOLE，但e.printStackTrace() 可印出較詳細的錯誤。
			return false;
		}
	}

	
	public boolean addbilladdNotification(NotificationRequest notificationRequest) {
		// 現在要寫讓通知進到DAO再進到資料庫!!!
		// NotificationVo newNotificationVo = notificationRepository.save(notification);
		try {
			// 需要透過 partyId 查 partyName，現在先寫死
			NotificationVo notification = NotificationVo.builder()
					.userId(notificationRequest.getUserIdsReceiveNotification().get(0))
					.title(NotificationType.BILL_ADD.getTitle())
					.content(NotificationType.BILL_ADD.getContent().formatted("partyId"))
					.status(NotificationStatus.UNREAD)// 預設狀態是未讀
					.createTime(LocalDateTime.now()).build();
			notificationRepository.save(notification);
			return true;
		} catch (Exception e) {
			e.printStackTrace(); // 也可用 System.out.println(e) ，執行後若有錯誤印出在CONSOLE，但e.printStackTrace() 可印出較詳細的錯誤。
			return false;
		}
	}
	@Override
	public List<NotificationVo> notificationjoin() {
		// TODO Auto-generated method stub
		return notificationRepository.findAll();
//			return null;
	}

	@Override
	public List<NotificationVo> notificationnewuser() {
		// TODO Auto-generated method stub
		return notificationRepository.findAll();
		// return null;
	}

	@Override
	public List<NotificationVo> notificationbilladd() {
		// TODO Auto-generated method stub
		return notificationRepository.findAll();
		// return null;
	}

}
