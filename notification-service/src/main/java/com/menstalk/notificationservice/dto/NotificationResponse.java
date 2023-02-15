package com.menstalk.notificationservice.dto;

import com.menstalk.notificationservice.domain.NotificationStatus;
import com.menstalk.notificationservice.domain.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse {
    private Long notificationId;
    private Long userId;
    private String title;
    private String content;
//    @Enumerated(EnumType.ORDINAL)
    private NotificationStatus status;
    private LocalDateTime createTime;
}
