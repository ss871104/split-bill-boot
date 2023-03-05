package com.menstalk.partyservice.controller;

import com.menstalk.partyservice.domain.Party;
import com.menstalk.partyservice.service.PartyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/findPartysByUserId")
    @ApiOperation("(External) find Partys by userId")
    public List<Party> findPartyByUserId(@RequestHeader(name = "id") Long userId) {

        return partyService.findPartysByPartyIds(userId);
    }


    @GetMapping("/findPartyNameByPartyId/{partyId}")
    @ApiOperation("(Internal) find PartyName by partyId")
    public String findPartyNameByPartyId(@PathVariable Long partyId) {

        return partyService.findPartyNameByPartyId(partyId);
    }

	@GetMapping("/admin/findAll")
	@ApiOperation("(Admin) FindAllPartys")
	public ResponseEntity<List<Party>> findAll(@RequestHeader(value = "role", required = false) String role) {
		try {
			if (role.equals("admin")) {
				return new ResponseEntity<>(partyService.getAllPartys(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}
}

