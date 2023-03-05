package com.menstalk.memberservice.controller;


import com.menstalk.memberservice.domain.Member;
import com.menstalk.memberservice.dto.AddMemberRequest;
import com.menstalk.memberservice.dto.BillAddedRequest;
import com.menstalk.memberservice.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/member")
@RestController
@RequiredArgsConstructor
//@CrossOrigin
@Api(tags = "Member Api")
public class MemberController {
	private final MemberService memberService;
	
	@GetMapping("/findUserIdByPartyId/{partyId}")
	@ApiOperation("(Internal)Find userid by partyId")
	public List<Long> findUserIdBypartyId(@PathVariable Long partyId) {
		return memberService.findUserIdByPartyId(partyId);
	}
	
	@GetMapping("/findUserInPartysByUserId/{userId}")
	@ApiOperation("(Internal)Find user in partys by userid")
	public List<Long> findUserInPartysByUserId(@PathVariable Long userId){
		return memberService.findUserInPartysByUserId(userId);
	}
	
	@GetMapping("/findMembersByPartyId/{partyId}")
	@ApiOperation("(Internal)Find members by partyId")
	public List<Member> findMembersByPartyId(@PathVariable Long partyId) {
		return memberService.findMembersByPartyId(partyId);
	}
	
	@PostMapping("/addMemberByCreateParty")
	@ApiOperation("(Internal)Add member by create party")
	public ResponseEntity<String> addMemberByCreateParty(@RequestBody Member member) {

		if (memberService.addMemberByCreateParty(member)) {
			return new ResponseEntity<String>("新增成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("新增失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PostMapping("/addMembers")
	@ApiOperation("(Internal)Add members")
	public ResponseEntity<String> addMembers(@RequestBody AddMemberRequest addMemberRequest) {

		if (memberService.addMembers(addMemberRequest)) {
			return new ResponseEntity<String>("新增成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("新增失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/updateStatus")
	@ApiOperation("(External)Update member status")
	public ResponseEntity<String> updateMember(@RequestParam("memberId") Long memberId) {

		if (memberService.updateStatus(memberId)) {
			return new ResponseEntity<String>("修改成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("修改失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	
	@DeleteMapping("/deleteMemberByPartyId/{partyId}")
	@ApiOperation("(Internal)Delete member by partyId")
	public ResponseEntity<String> deleteAllByPartyId(@PathVariable Long partyId) {
		if (memberService.deleteMemberByPartyId(partyId)) {
			return new ResponseEntity<String>("刪除成功", HttpStatus.ACCEPTED);
		}
		else {
			return new ResponseEntity<String>("刪除失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	@DeleteMapping("/deleteMemberById")
	@ApiOperation("(External)Delete member by id")
	public ResponseEntity<String> deleteMemberById(@RequestParam("memberId") Long memberId) {
		if (memberService.deleteMemberById(memberId)) {
			return new ResponseEntity<String>("刪除成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("刪除失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/updateBalanceAdd")
	@ApiOperation("(Internal)Update balance when added")
	public ResponseEntity<String> updateBalanceAdd(@RequestBody List<BillAddedRequest> list) {
		if (memberService.updateBalanceAdd(list)) {
			return new ResponseEntity<String>("修改金額成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("修改金額失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/updateBalanceDelete")
	@ApiOperation("(Internal)Update balance when deleted")
	public ResponseEntity<String> updateBalanceDelete(@RequestBody List<BillAddedRequest> list) {
		if (memberService.updateBalanceDelete(list)) {
			return new ResponseEntity<String>("修改金額成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("修改金額失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping("/admin/findAll")
	@ApiOperation("(Admin) FindAllMembers")
	public ResponseEntity<List<Member>> findAll(@RequestHeader(value = "role", required = false) String role) {
		try {
			if (role.equals("admin")) {
				return new ResponseEntity<>(memberService.getAllMembers(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
