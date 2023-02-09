package com.menstalk.billservice.service;

import java.util.List;

import com.menstalk.billservice.domain.BillDetail;

public interface BillDetailService {
	
	public List<BillDetail> selectByBillId(Long billId);
	public boolean addBillDetail(BillDetail billDetail);
	public boolean removeBillDetail(Long billDetailId);

}
