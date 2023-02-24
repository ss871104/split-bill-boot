package com.menstalk.notificationservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("member-service")
public interface MemberProxy {
	
	@GetMapping("/findUserIdByPartyId/{partyId}")
	public List<Long> findUserIdBypartyId(@PathVariable Long partyId);

}
