package com.menstalk.memberservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menstalk.memberservice.domain.Member;
import com.menstalk.memberservice.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/member")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/{partyId}")
	public List<Member> selectByPartyId(@PathVariable Long partyId) {
		return memberService.selectByPartyId(partyId);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addMembers(@RequestBody Member member) {

		if (memberService.addMembers(member)) {
			return new ResponseEntity<String>("新增成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("新增失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateMember(@RequestBody Member member) {

		if (memberService.updateMember(member)) {
			return new ResponseEntity<String>("修改成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("修改失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteMember(@PathVariable Long id) {
		if (memberService.deleteMember(id)) {
			return new ResponseEntity<String>("刪除成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("刪除失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}

}
