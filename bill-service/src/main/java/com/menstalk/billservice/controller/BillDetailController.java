package com.menstalk.billservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.menstalk.billservice.domain.BillDetail;
import com.menstalk.billservice.service.BillDetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bill_detail")
@RequiredArgsConstructor
@CrossOrigin
public class BillDetailController {
	
	private final BillDetailService billDetailService;
	
	@GetMapping("/{billId}")
	@ResponseStatus(HttpStatus.OK)
	public List<BillDetail> selectById(@PathVariable Long billId) {
		
		return billDetailService.selectByBillId(billId);
		
	}

}
