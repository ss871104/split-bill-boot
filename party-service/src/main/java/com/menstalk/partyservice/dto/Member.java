package com.menstalk.partyservice.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter //用於自動生成類中的getter方法。
@Setter //用於自動生成類中的setter方法。
@ToString //用於自動生成toString方法，可用於調試和日誌輸出。
@Builder //用於生成一個建構器，可以使用具有清晰語義的方式設置對象的屬性。
@AllArgsConstructor //用於生成一個帶有所有參數的全參數構造器。
@NoArgsConstructor //用於生成一個無參構造器。
public class Member {

	private Long memberId;
	private Long partyId;
	private Long userId;
	private Long balance;
	private String memberNickname;
	private MemberStatus memberStatus;
	private LocalDateTime createTime;
}
