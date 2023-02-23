package com.menstalk.partyservice.proxy;

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
	
//    @GetMapping("/api/member/finduserId/{userId}")
//    public ResponseEntity<String> find(@PathVariable("userId") @RequestBody Long  partyId);
}
