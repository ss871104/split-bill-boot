package com.menstalk.memberservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillAddedRequest {

	private Long memberId;
	private BillDetailType billDetailType;
	private Long amount;
}
