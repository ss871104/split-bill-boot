package com.menstalk.memberservice.domin;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;
	private Long partyId;
	private Long userId;
	private Long balance;
	private String memberNickname;
	private Integer memberStatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime createTime;
}
