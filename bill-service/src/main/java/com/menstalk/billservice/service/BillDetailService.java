package com.menstalk.billservice.service;

import com.menstalk.billservice.domain.BillDetail;

import java.util.List;

public interface BillDetailService {
	
	public List<BillDetail> selectByBillId(Long billId);
	public boolean addBillDetail(List<BillDetail> billDetailList);
	public boolean removeBillDetail(Long billDetailId);

}
