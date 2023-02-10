package com.menstalk.billservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.menstalk.billservice.domain.Bill;
import com.menstalk.billservice.domain.BillDetail;
import com.menstalk.billservice.domain.BillDetailType;
import com.menstalk.billservice.dto.BillPlacedRequest;
import com.menstalk.billservice.mapper.BillMapper;
import com.menstalk.billservice.repository.BillRepository;
import com.menstalk.billservice.repository.BillDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService{
	
	private final BillRepository billRepository;
	private final BillDetailService billDetailService;
	private final BillMapper billMapper;

	@Override
	public List<Bill> selectByPartyId(Long partyId) {
		
		List<Bill> list = new ArrayList<>();
		list = billRepository.findByPartyId(partyId);
		return list;
	}
	
	@Override
	public boolean addBillTransfer(BillPlacedRequest billPlacedRequest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBillAA(BillPlacedRequest billPlacedRequest) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBillGoDutch(BillPlacedRequest billPlacedRequest) {		
		
		try {
			Bill bill =  billRepository.save(billMapper.billPlacedRequestToBill(billPlacedRequest));
			billRepository.flush();
			Long billId = bill.getBillId();
			
			List<BillDetail> billDetailList = billPlacedRequest.getMemberIdMap().entrySet().stream()
												.map(x -> BillDetail.builder()
														.billId(billId)
														.billDetailType(BillDetailType.EXPENSE)
														.memberId(x.getKey())
														.amount(x.getValue())
														.build())
												.collect(Collectors.toList());
			billDetailList.add(BillDetail.builder()
										.billId(billId)
										.billDetailType(BillDetailType.INCOME)
										.memberId(billPlacedRequest.getMemberId())
										.amount(billPlacedRequest.getTotalAmount())
										.build());
	
			billDetailService.addBillDetail(billDetailList);
			
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
