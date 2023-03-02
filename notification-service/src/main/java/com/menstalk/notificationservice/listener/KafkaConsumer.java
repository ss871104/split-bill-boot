package com.menstalk.notificationservice.listener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.menstalk.notificationservice.domain.Notification;
import com.menstalk.notificationservice.domain.NotificationStatus;
import com.menstalk.notificationservice.domain.NotificationType;
import com.menstalk.notificationservice.dto.InviteMemberRequest;
import com.menstalk.notificationservice.proxy.PartyProxy;
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
	private final PartyProxy partyProxy;

	@KafkaListener(topics = "inviteMemberTopic")
	public void inviteMemberNotification(InviteMemberRequest inviteMemberRequest) {
		String partyName = partyProxy.findPartyNameByPartyId(inviteMemberRequest.getPartyId());
		Notification notification = Notification.builder()
						.userId(inviteMemberRequest.getUserId())
						.title(NotificationType.INVITE.getTitle())
						.content(NotificationType.INVITE.getContent().formatted(partyName))
						.createTime(LocalDateTime.now())
						.status(NotificationStatus.UNREAD)
						.memberId(inviteMemberRequest.getMemberId())
						.build();

		notificationRepository.save(notification);

		log.info("Retreived Notification: INVITE, '{}' to '{}'", inviteMemberRequest.getUserId(), partyName);
	}
	

}
