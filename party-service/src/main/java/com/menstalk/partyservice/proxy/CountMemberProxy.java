package com.menstalk.partyservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient("member-service")
public interface CountMemberProxy {
	
	@GetMapping("/api/member/countMember/{partyId}")
	public Long countMember(@PathVariable("partyId") Long partyId);
	

}
