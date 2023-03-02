package com.menstalk.memberservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddMemberRequest {
    private Long partyId;
    private List<Long> userIdList;
}
