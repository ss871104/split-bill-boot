package com.menstalk.memberservice.domain;

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
	private MemberStatus memberStatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime createTime;
}
