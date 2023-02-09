package com.menstalk.billservice.domain;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bill {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bill_id")
	private Long billId;
	@Column(name="party_id")
	private Long partyId;
	@Column(name="member_id")
	private Long memberId;
	@Column(name="bill_name")
	private String billName;
	@Column(name="bill_type")
	@Enumerated(EnumType.ORDINAL)
	private BillType billType;
	@Column(name="total_amount")
	private Long totalAmount;
	@Column(name = "create_time", updatable = false, insertable = false, nullable = false,
	        columnDefinition = "datetime default CURRENT_TIMESTAMP")
	private LocalDateTime createTime;

}
