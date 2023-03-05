package com.menstalk.billservice.controller;

import com.menstalk.billservice.domain.BillDetail;
import com.menstalk.billservice.service.BillDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bill_detail")
@RequiredArgsConstructor
//@CrossOrigin
@Api(tags = "BillDetail Api")
public class BillDetailController {
	
	private final BillDetailService billDetailService;
	
	@GetMapping("/{billId}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("(External) Show BillDetail by BillId")
	public List<BillDetail> selectById(@PathVariable Long billId) {
		
		return billDetailService.selectByBillId(billId);
		
	}

}
