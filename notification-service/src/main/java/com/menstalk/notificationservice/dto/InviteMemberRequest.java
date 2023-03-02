package com.menstalk.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InviteMemberRequest {
    private Long notificationId;
    private Long userId;
    private Long memberId;
}
