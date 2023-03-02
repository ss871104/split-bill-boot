package com.menstalk.notificationservice.listener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.menstalk.notificationservice.dto.InviteMemberRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.menstalk.notificationservice.dto.NewBillRequest;
import com.menstalk.notificationservice.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
// 記得要用Kafka之前要先用Docker安裝Kafka(先裝Zookeeper後裝Kafka)，快速的方法是開CMD找到docker-compose的路徑，並打指令：docker compose up -d
public class KafkaConsumer {
	private final NotificationRepository notificationRepository;

	@KafkaListener(topics = "inviteMemberTopic")
	public void inviteMemberNotification(InviteMemberRequest inviteMemberRequest) {


		log.info("Retreived Notification: INVITE, '{}'", inviteMemberRequest.getUserId());
	}
	

}
