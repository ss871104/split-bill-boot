package com.menstalk.notificationservice.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor // 全參數建構子
@NoArgsConstructor // 無參數建構子
@Entity // 對應資料庫類別
@Table(name = "notification")
public class Notification {
	@Id // 讓資料庫對應的ID
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notificationId;
	private Long userId;
	private String title;
	private String content;
	@Enumerated(EnumType.ORDINAL)
	private NotificationStatus status;
	private LocalDateTime createTime;
	private Long memberId;

}
