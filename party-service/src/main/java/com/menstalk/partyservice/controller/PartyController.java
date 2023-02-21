package com.menstalk.partyservice.controller;

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

import com.menstalk.partyservice.domain.Party;
import com.menstalk.partyservice.dto.CountMember;
import com.menstalk.partyservice.service.PartyService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/party")
@RestController
@RequiredArgsConstructor
@CrossOrigin

public class PartyController {

	private final PartyService partyService;

	@GetMapping("/{partyId}")
	public Long countMember(@PathVariable Long partyId) {
		return partyService.countMemberQuantityByPartyId(partyId);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addParty(@RequestBody Party party) {
		// System.out.println("======"); 斷點
		if (partyService.addParty(party)) {
			return new ResponseEntity<String>("新增成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("新增失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateParty(@RequestBody Party party) {

		if (partyService.updateParty(party)) {
			return new ResponseEntity<String>("修改成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("修改失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}
//
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<String> deleteMember(@PathVariable Long id) {
//		if (partyService.deleteParty(id)) {
//			return new ResponseEntity<String>("刪除成功", HttpStatus.ACCEPTED);
//		} else {
//			return new ResponseEntity<String>("刪除失敗", HttpStatus.NOT_ACCEPTABLE);
//		}
//	}
//
////	@PutMapping("/updateBalanceAdd")
////	public ResponseEntity<String> updateBalanceAdd(@RequestBody List<BillAddedRequest> list) {
////		if (memberService.updateBalanceAdd(list)) {
////			return new ResponseEntity<String>("修改金額成功", HttpStatus.ACCEPTED);
////		} else {
////			return new ResponseEntity<String>("修改金額失敗", HttpStatus.NOT_ACCEPTABLE);
////		}
//	@GetMapping
//	public List<PartyResponse> @RequestBody(List<Long> partyIds);
//	}

}

