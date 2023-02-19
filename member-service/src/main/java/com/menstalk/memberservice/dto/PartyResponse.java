package com.menstalk.memberservice.dto;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartyResponse {
	private Long partyId;
	private Long userId;
	private String pname;
	private Long memberQuantity;
	private LocalDateTime createTime;


}
