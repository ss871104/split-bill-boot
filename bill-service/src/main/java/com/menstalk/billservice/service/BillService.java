package com.menstalk.billservice.service;

import java.util.List;

import com.menstalk.billservice.domain.Bill;

public interface BillService {
	
	public List<Bill> selectByPartyId(Long id);
	public boolean addBillAA(Bill bill);
	public boolean remove(Long id);

}
