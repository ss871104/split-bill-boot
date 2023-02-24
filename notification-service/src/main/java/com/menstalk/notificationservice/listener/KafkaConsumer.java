package com.menstalk.notificationservice.listener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.menstalk.notificationservice.domain.Notification;
import com.menstalk.notificationservice.domain.NotificationStatus;
import com.menstalk.notificationservice.domain.NotificationType;
import com.menstalk.notificationservice.dto.NewBillRequest;
import com.menstalk.notificationservice.dto.NewUserRequest;
import com.menstalk.notificationservice.proxy.MemberProxy;
import com.menstalk.notificationservice.proxy.PartyProxy;
import com.menstalk.notificationservice.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

// 記得要用Kafka之前要先用Docker安裝Kafka(先裝Zookeeper後裝Kafka)，快速的方法是開CMD找到docker-compose的路徑，並打指令：docker compose up -d
public class KafkaConsumer {
	private final NotificationRepository notificationRepository;
	private final MemberProxy memberProxy;
	private final PartyProxy partyProxy;
	
	@KafkaListener(topics = "newUserTopic")
	public void newUserNotification(NewUserRequest newUserRequest) {
		Notification notification = Notification.builder()
				.userId(newUserRequest.getUserId())
				.title(NotificationType.NEW_USER.getTitle())
				.content(NotificationType.NEW_USER.getContent().formatted(newUserRequest.getName()))
				.status(NotificationStatus.UNREAD)
				.createTime(LocalDateTime.now())
				.build();
		notificationRepository.save(notification);
		
		log.info("Retreived Notification: NEW_USER, '{}'", newUserRequest.getUserId());
	}
	
	@KafkaListener(topics = "newBillTopic")
	public void newBillNotification(NewBillRequest newBillRequest) {
		
		String partyName = partyProxy.findPartyNameByPartyId(newBillRequest.getPartyId());
		List<Long> userIds = memberProxy.findUserIdBypartyId(newBillRequest.getPartyId());

		List<Notification> notificationList = userIds.stream()
				.map(x -> Notification.builder()
					.userId(x)
					.title(NotificationType.BILL_ADD.getTitle())
					.content(NotificationType.BILL_ADD.getContent().formatted(partyName))
					.status(NotificationStatus.UNREAD)
					.createTime(LocalDateTime.now())
					.build())
				.collect(Collectors.toList());

		notificationRepository.saveAll(notificationList);
		
		log.info("Retreived Notification: BILL_ADD, '{}'", partyName);
	}
	

}
