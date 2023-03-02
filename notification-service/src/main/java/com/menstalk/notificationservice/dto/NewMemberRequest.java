package com.menstalk.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewMemberRequest {
    private Long notificationId;
    private Long partyId;
    private Long memberName;
}
