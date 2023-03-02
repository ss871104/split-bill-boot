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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.menstalk.partyservice.domain.Party;
import com.menstalk.partyservice.dto.CountMember;
import com.menstalk.partyservice.service.PartyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/party")
@RestController
@RequiredArgsConstructor
@Api(tags = "Party Api")

public class PartyController {

	private final PartyService partyService;

	@PostMapping("/add")
	@ApiOperation("(External) Add by partyname")
	public ResponseEntity<String> addParty(@RequestBody Party party, @RequestHeader(name = "id") String userId) {
		if (partyService.addParty(party, Long.valueOf(userId))) {
			return new ResponseEntity<String>("新增成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("新增失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/update/{partyId}")
	@ApiOperation("(External) update by partyname")
	public ResponseEntity<String> updateParty(@PathVariable Long partyId,
			@RequestParam(value = "partyName", required = true) String partyName) {

		if (partyService.updateParty(partyId, partyName)) {
			return new ResponseEntity<String>("修改成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("修改失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@DeleteMapping("/delete/{partyId}")
	@ApiOperation("(External) delete by partyname, partyId, memberQuantity ")
	public ResponseEntity<String> deleteParty(@PathVariable Long partyId) {
		if (partyService.deleteParty(partyId)) {
			return new ResponseEntity<String>("刪除成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("刪除失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PutMapping("/updateQty/{partyId}")
	@ApiOperation("(External) update by memberQuantity")
	public ResponseEntity<String> updateQty(@PathVariable Long partyId,
			@RequestParam(value = "memberQty", required = true) Long memberQty) {

		if (partyService.updateMemberQty(partyId, memberQty)) {
			return new ResponseEntity<String>("修改成功", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("修改失敗", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping("/findPartysByPartyIds")
	@ApiOperation("(Internal) find Partys by partyId")
	public List<Party> findPartyByUserId(@RequestHeader(name = "id") Long userId) {
		
			return partyService.findPartysByPartyIds(userId);

		}
	

	@GetMapping("/findPartyNameByPartyId/{partyId}")
	@ApiOperation("(Ixternal) find PartyName by partyId")
	public String findPartyNameByPartyId(@PathVariable Long partyId) {
		
			return partyService.findPartyNameByPartyId(partyId);
		}
	}

