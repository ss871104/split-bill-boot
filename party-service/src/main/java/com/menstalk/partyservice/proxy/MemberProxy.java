package com.menstalk.partyservice.proxy;

import com.menstalk.partyservice.dto.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient("member-service")
public interface MemberProxy {
	
	@GetMapping("/api/member/countMember/{partyId}")
	public Long countMember(@PathVariable("partyId") Long partyId);
	
	@PostMapping("/api/member/addMemberByCreateParty")
	public ResponseEntity<String> addMemberByCreateParty(@RequestBody Member member);
	
   @GetMapping("/api/member/findUserInPartysByUserId/{userId}")
    public  List<Long>findUserInPartysByUserId(@PathVariable("userId") Long userId) ;

	
	@DeleteMapping ("/api/member/deleteMemberByPartyId/{partyId}")
	public  ResponseEntity<String> deleteAllByPartyId(@PathVariable("partyId") Long partyId);
}

