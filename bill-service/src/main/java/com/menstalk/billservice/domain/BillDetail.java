package com.menstalk.billservice.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BillDetail {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bill_detail_id")
	private Long billDetailId;
	@Column(name="bill_id")
	private Long billId;
	@Column(name="member_id")
	private Long memberId;
	@Column(name="bill_detail_name")
	private String billDetailName;
	@Column(name="bill_detail_type")
	@Enumerated(EnumType.ORDINAL)
	private BillDetailType billDetailType;
	private Long amount;
}
