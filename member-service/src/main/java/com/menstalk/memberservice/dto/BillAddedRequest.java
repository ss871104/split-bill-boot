package com.menstalk.memberservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillAddedRequest {

	private BillDetailType billDetailType;
	private Long memberId;
	private Long amount;
}
/**
 * DTO(DatatransferObject)在計算機編程中，數據傳輸對象 是在2個進程中攜帶數據的對象。
 * 因為進程間通信通常用於遠程接口的昂貴操作。
 * 成本的主體是客戶和伺服器之間的來回通信時間。為降低這種調用次數，使用DTO聚合本來需要多次通信傳輸的數據。
 * 
 * 因為需要使用到兩種VO裡的特定資料流
 * 使用DTO將特定資料流綁定起來傳輸
 * 
 * @param billAddedRequest
 * @return
 */
