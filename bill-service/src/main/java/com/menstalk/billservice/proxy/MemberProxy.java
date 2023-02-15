package com.menstalk.billservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.menstalk.billservice.dto.BillAddedRequest;


@FeignClient("member-service")
public interface MemberProxy {
	
	@PutMapping("/api/member/updateBalanceAdd")
	public void updateBalanceByAdd(@RequestBody List<BillAddedRequest> list);

}
