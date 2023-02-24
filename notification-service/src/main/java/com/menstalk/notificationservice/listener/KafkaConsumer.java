package com.menstalk.notificationservice.listener;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.menstalk.notificationservice.domain.Notification;
import com.menstalk.notificationservice.domain.NotificationStatus;
import com.menstalk.notificationservice.domain.NotificationType;
import com.menstalk.notificationservice.dto.NewUserRequest;
import com.menstalk.notificationservice.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

// 記得要用Kafka之前要先用Docker安裝Kafka(先裝Zookeeper後裝Kafka)，快速的方法是開CMD找到docker-compose的路徑，並打指令：docker compose up -d
public class KafkaConsumer {
	private final NotificationRepository notificationRepository;
	
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
	

}
