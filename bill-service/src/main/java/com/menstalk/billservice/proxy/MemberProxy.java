package com.menstalk.billservice.proxy;

import java.util.List;

import com.menstalk.billservice.dto.Member;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.menstalk.billservice.dto.BillAddedRequest;


@FeignClient("member-service")
public interface MemberProxy {
	
	@PutMapping("/api/member/updateBalanceAdd")
	public void updateBalanceByAdd(@RequestBody List<BillAddedRequest> list);
	
	@PutMapping("/api/member/updateBalanceDelete")
	public void updateBalanceByDelete(@RequestBody List<BillAddedRequest> list);

	@GetMapping("/findMembersByPartyId/{partyId}")
	public List<Member> findMembersByPartyId(@PathVariable("partyId") Long partyId);

}
