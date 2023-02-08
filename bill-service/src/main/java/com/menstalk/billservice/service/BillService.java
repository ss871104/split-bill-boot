package com.menstalk.billservice.service;

import java.util.List;

import com.menstalk.billservice.domain.Bill;

public interface BillService {
	
	public List<Bill> selectByPartyId(Long partyId);
	public boolean addBillAA(Bill bill);
	public boolean updateBill(Long billId);
	public boolean remove(Long billId);

}
