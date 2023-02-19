package com.menstalk.memberservice.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

@Getter //用於自動生成類中的getter方法。
@Setter //用於自動生成類中的setter方法。
@ToString //用於自動生成toString方法，可用於調試和日誌輸出。
@Builder //用於生成一個建構器，可以使用具有清晰語義的方式設置對象的屬性。
@AllArgsConstructor //用於生成一個帶有所有參數的全參數構造器。
@NoArgsConstructor //用於生成一個無參構造器。
@Entity //用於定義一個實體類，該類的對象將映射到數據庫中的表。通常與Hibernate框架一起使用。
public class Member {

	@Id //指定實體類中的主鍵屬性，可以用於標記一個或多個屬性作為主鍵。
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//指定生成主鍵的策略。有多種策略可用，如IDENTITY、SEQUENCE、TABLE等。
	//指定使用自動增量方式生成主鍵。當插入一條新的數據時，數據庫會自動生成一個唯一的主鍵值，不需要手動指定。
	private Long memberId;
	private Long partyId;
	private Long userId;
	private Long balance;
	private String memberNickname;
	private MemberStatus memberStatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	//是Spring框架中用於解析和格式化日期時間類型的字符串的注解，可以指定日期時間的格式，方便進行操作。
	@Column(name = "create_time", updatable = false, insertable = false, nullable = false,
    columnDefinition = "datetime default CURRENT_TIMESTAMP")
	//是JPA中的注解，用於標記實體類中的屬性與數據表中的列的映射關係。
	private LocalDateTime createTime;
}
