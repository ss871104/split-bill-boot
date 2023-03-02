package com.menstalk.notificationservice.dto;

import com.menstalk.notificationservice.domain.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private NotificationStatus status;
    private LocalDateTime createTime;
    private Long memberId;

}
