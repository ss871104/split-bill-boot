package com.menstalk.billservice.service;

import java.util.List;

import com.menstalk.billservice.domain.Bill;
import com.menstalk.billservice.dto.BillPlacedRequest;

public interface BillService {
	
	public List<Bill> selectByPartyId(Long partyId);
	public boolean addBillTransfer(BillPlacedRequest billPlacedRequest);
	public boolean addBillAA(BillPlacedRequest billPlacedRequest);
	public boolean addBillGoDutch(BillPlacedRequest billPlacedRequest);
	public boolean removeBill(Long billId);
	
	public boolean updateBillTransfer(BillPlacedRequest billPlacedRequest);
	public boolean updateBillAA(BillPlacedRequest billPlacedRequest);
	public boolean updateBillGoDutch(BillPlacedRequest billPlacedRequest);

}
