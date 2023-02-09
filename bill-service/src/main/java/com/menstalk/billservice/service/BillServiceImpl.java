package com.menstalk.billservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.menstalk.billservice.domain.Bill;
import com.menstalk.billservice.repository.BillRepository;
import com.menstalk.billservice.repository.BillDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService{
	
	private final BillRepository billRepository;
	private final BillDetailRepository billDetailRepository;

	@Override
	public List<Bill> selectByPartyId(Long partyId) {
		
		List<Bill> list = new ArrayList<>();
		list = billRepository.findByPartyId(partyId);
		return list;
	}

	@Override
	public boolean addBillAA(Bill bill) {		
		
		try {
			billRepository.save(bill);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateBill(Bill billId) {
		
		try {
			billRepository.save(billId);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removeBill(Long billId) {
		try {
			billRepository.deleteById(billId);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
