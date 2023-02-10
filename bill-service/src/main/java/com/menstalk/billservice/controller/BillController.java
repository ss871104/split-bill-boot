package com.menstalk.billservice.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.menstalk.billservice.domain.Bill;
import com.menstalk.billservice.domain.BillType;
import com.menstalk.billservice.dto.BillPlacedRequest;
import com.menstalk.billservice.service.BillService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bill")
@RequiredArgsConstructor
@CrossOrigin
public class BillController {
	
	private final BillService billService;
	
	@GetMapping("/{partyId}")
	@ResponseStatus(HttpStatus.OK)
	public List<Bill> selectById(@PathVariable Long partyId) {

		return billService.selectByPartyId(partyId);
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addBill(@RequestBody BillPlacedRequest billPlacedRequest) {
		
		if (billPlacedRequest.getBillType() == BillType.AA) {
			billService.addBillAA(billPlacedRequest);
		} else if (billPlacedRequest.getBillType() == BillType.GO_DUTCH) {
			billService.addBillGoDutch(billPlacedRequest);
		} else if (billPlacedRequest.getBillType() == BillType.TRANSFER) {
			billService.addBillTransfer(billPlacedRequest);
		} else {
			return new ResponseEntity<String>("新增失敗", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("新增成功", HttpStatus.ACCEPTED);
		
//		if(billService.addBillAA(billAA)) {
//			return new ResponseEntity<String>("新增成功", HttpStatus.ACCEPTED);			
//		}else {
//			return new ResponseEntity<String>("新增失敗", HttpStatus.NOT_ACCEPTABLE);			
//			
//		}					
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateBill(@RequestBody Bill bill) {
		
		if(billService.updateBill(bill)) {
			return new ResponseEntity<String>("修改成功", HttpStatus.ACCEPTED);			
		}else {
			return new ResponseEntity<String>("修改失敗", HttpStatus.NOT_ACCEPTABLE);			
			
		}					
	}
	
	@DeleteMapping("/delete/{billId}")
	public ResponseEntity<String> removeBill(@PathVariable Long billId) {
		
		if(billService.removeBill(billId)) {
			return new ResponseEntity<String>("刪除成功", HttpStatus.ACCEPTED);			
		}else {
			return new ResponseEntity<String>("刪除失敗", HttpStatus.NOT_ACCEPTABLE);			
			
		}					
	}

}
