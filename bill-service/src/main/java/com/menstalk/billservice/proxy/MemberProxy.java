package com.menstalk.billservice.proxy;

import com.menstalk.billservice.dto.BillAddedRequest;
import com.menstalk.billservice.dto.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient("member-service")
public interface MemberProxy {
	
	@PutMapping("/api/member/updateBalanceAdd")
	public void updateBalanceByAdd(@RequestBody List<BillAddedRequest> list);
	
	@PutMapping("/api/member/updateBalanceDelete")
	public void updateBalanceByDelete(@RequestBody List<BillAddedRequest> list);

	@GetMapping("/api/member/findMembersByPartyId/{partyId}")
	public List<Member> findMembersByPartyId(@PathVariable("partyId") Long partyId);

}
