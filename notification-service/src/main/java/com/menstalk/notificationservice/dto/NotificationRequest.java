package com.menstalk.notificationservice.dto;

import com.menstalk.notificationservice.domain.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationRequest {
    private Long userId;
    private Long partyId;
    private Long memberId;
    private List<Long> userIdsReceiveNotification;
}
