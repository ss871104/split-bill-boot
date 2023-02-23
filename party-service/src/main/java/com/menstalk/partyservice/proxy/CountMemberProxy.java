package com.menstalk.partyservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.menstalk.partyservice.dto.Member;


@FeignClient("member-service")
public interface CountMemberProxy {
	
	@GetMapping("/api/member/countMember/{partyId}")
	public Long countMember(@PathVariable("partyId") Long partyId);
	
	@PostMapping("/api/member/add")
	public ResponseEntity<String> addMembers(@RequestBody Member member);
	
   @GetMapping("/api/member/findUserInPartysByUserId/{userId}")
    public static List<Long>findUserInPartysByUserId(@PathVariable Long userId) {
	// TODO Auto-generated method stub
	return null;
}
}
