package com.menstalk.partyservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.menstalk.partyservice.dto.Member;


@FeignClient("member-service")
public interface MemberProxy {
	
	@GetMapping("/api/member/countMember/{partyId}")
	public Long countMember(@PathVariable("partyId") Long partyId);
	
	@PostMapping("/api/member/addMemberByCreateParty")
	public ResponseEntity<String> addMemberByCreateParty(@RequestBody Member member);
	
   @GetMapping("/api/member/findUserInPartysByUserId/{userId}")
    public  List<Long>findUserInPartysByUserId(@PathVariable Long userId) ;

	
	@DeleteMapping ("/api/member/deleteMemberByPartyId/{partyId}")
	public  ResponseEntity<String> deleteAllByPartyId(@PathVariable Long partyId);
}

