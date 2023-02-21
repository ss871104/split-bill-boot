package com.menstalk.billservice.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.menstalk.billservice.domain.BillType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillPlacedRequest {
	private Long billId;
	private Long partyId;
	private Long memberId;
	private String billName;
	private BillType billType;
	private Long totalAmount;
	private LocalDateTime createTime;
	private Map<Long, Long> memberIdMapExpense;
	
	private Map<Long, Long> memberIdMapIncome;

}
