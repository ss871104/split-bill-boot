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
	private Long billDetailId;
	private Long billId;
	private Long memberId;
	private String billDetailName;
	private Integer billType;
	private Long amount;
	

}
