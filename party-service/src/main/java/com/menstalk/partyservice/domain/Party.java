package com.menstalk.partyservice.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor // 全參數建構子
@NoArgsConstructor // 無參數建構子
@Entity // 對應資料庫類別
@Table(name = "party")
public class Party {
	@Id // 讓資料庫對應的ID
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="party_id")
	private Long partyId;
	// private Long userId;
	@Column(name="party_name")
	private String partyName;
	@Column(name="member_quantity")
	private Long memberQuantity;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "create_time", updatable = false, insertable = false, nullable = false,
    columnDefinition = "datetime default CURRENT_TIMESTAMP")
	private LocalDateTime createTime;
}
