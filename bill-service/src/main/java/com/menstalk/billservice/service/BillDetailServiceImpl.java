package com.menstalk.billservice.service;

import com.menstalk.billservice.domain.BillDetail;
import com.menstalk.billservice.repository.BillDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillDetailServiceImpl implements BillDetailService {
	
	private final BillDetailRepository billDetailRepository;

	@Override
	public List<BillDetail> selectByBillId(Long billId) {
		List<BillDetail> list = new ArrayList<>();
		list = billDetailRepository.findByBillId(billId);
		return list;
	}

	@Override
	public boolean addBillDetail(List<BillDetail> billDetailList) {
		
		try {
			billDetailRepository.saveAll(billDetailList);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean removeBillDetail(Long billId) {
		
		try {
			
			billDetailRepository.deleteAllByBillId(billId);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<BillDetail> getAllBillDetails() {
		return billDetailRepository.findAll();
	}
}
