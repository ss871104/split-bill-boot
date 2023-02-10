package com.menstalk.billservice.dto;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.menstalk.billservice.domain.BillType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillPlacedRequest {
	private Long partyId;
	private Long memberId;
	private String billName;
	private BillType billType;
	private Long totalAmount;
	private Map<Long, Long> memberIdMap;

}
