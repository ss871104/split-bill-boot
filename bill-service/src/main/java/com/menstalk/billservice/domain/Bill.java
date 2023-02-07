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
	private Long billId;
	private Long partyId;
	private Long memberId;
	private String billName;
	private Integer type;	
	private Long totalAmount;
	private LocalDateTime createTime;

}
