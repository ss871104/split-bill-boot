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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse {
    private Long notificationId;
    private Long userId;
    private String title;
    private String content;
    private NotificationStatus status;
    private LocalDateTime createTime;
    private String acceptUrl;
    private String refuseUrl;

}
