package com.menstalk.billservice.controller;

import com.menstalk.billservice.domain.BillDetail;
import com.menstalk.billservice.service.BillDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@GetMapping("/admin/findAll")
	@ApiOperation("(Admin) FindAllBillDetails")
	public ResponseEntity<List<BillDetail>> findAll(@RequestHeader(value = "role", required = false) String role) {
		try {
			if (role.equals("admin")) {
				return new ResponseEntity<>(billDetailService.getAllBillDetails(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

}
