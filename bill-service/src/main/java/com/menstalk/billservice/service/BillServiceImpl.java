package com.menstalk.billservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.menstalk.billservice.domain.Bill;
import com.menstalk.billservice.domain.BillDetail;
import com.menstalk.billservice.domain.BillDetailType;
import com.menstalk.billservice.dto.BillAddedRequest;
import com.menstalk.billservice.dto.BillPlacedRequest;
import com.menstalk.billservice.mapper.BillMapper;
import com.menstalk.billservice.proxy.MemberProxy;
import com.menstalk.billservice.repository.BillDetailRepository;
import com.menstalk.billservice.repository.BillRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
@Primary
public class BillServiceImpl implements BillService {

	private final BillRepository billRepository;
	private final BillDetailRepository billDetailRepository;
	private final BillDetailService billDetailService;
	private final BillMapper billMapper;
	private final MemberProxy memberProxy;
	
	private final OtherBillServices otherBillServices;

	@Override
	public List<Bill> selectByPartyId(Long partyId) {

		List<Bill> list = new ArrayList<>();
		list = billRepository.findByPartyId(partyId);
		return list;
	}

	@Override
	public boolean addBillTransfer(BillPlacedRequest billPlacedRequest) {

		try {
			Bill bill = billRepository.save(billMapper.billPlacedRequestToBill(billPlacedRequest));
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
			
			
			// 將BillDetailList轉型為BillAddRequest
			List<BillAddedRequest> billAddedRequest = billDetailList.stream()
					.map(x -> billMapper.billDetailToBillAddedRequest(x))
					.collect(Collectors.toList());
			// 利用OpenFeign抓updateBalance的API
			memberProxy.updateBalanceByAdd(billAddedRequest);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean addBillAA(BillPlacedRequest billPlacedRequest) {
		try {
			Bill bill = billRepository.save(billMapper.billPlacedRequestToBill(billPlacedRequest));
			billRepository.flush();
			Long billId = bill.getBillId();

			List<BillDetail> billDetailList = new ArrayList<>();

			if (bill.getTotalAmount() % billPlacedRequest.getMemberIdMap().size() == 0) {

				billDetailList = billPlacedRequest.getMemberIdMap().entrySet().stream()
						.map(x -> BillDetail.builder()
								.billId(billId).billDetailType(BillDetailType.EXPENSE)
								.memberId(x.getKey())
								.amount(bill.getTotalAmount() / billPlacedRequest.getMemberIdMap().size())
								.build())
						.collect(Collectors.toList());

			} else {
				// 金額餘數
				int remain = (int) (bill.getTotalAmount() % billPlacedRequest.getMemberIdMap().size());
				// 參與的 memberId
				List<Long> memberIdList = new ArrayList<>(billPlacedRequest.getMemberIdMap().keySet());
				// 被隨機抽到要負擔餘數的 memberId
				List<Long> unluckyMember = new ArrayList<>();
				// 隨機用的
				for (int i = 0; i < remain; i++) {
					unluckyMember.add(memberIdList.get((int) (Math.random() * memberIdList.size())));
					memberIdList.remove(unluckyMember.get(i));
				}
				// 被抽到要負擔餘數的人的 billDetail
				List<BillDetail> unluckyList = billPlacedRequest.getMemberIdMap().entrySet().stream()
						.filter(x -> unluckyMember.contains(x.getKey()))
						.map(x -> BillDetail.builder()
								.billId(billId).billDetailType(BillDetailType.EXPENSE)
								.memberId(x.getKey())
								.amount((bill.getTotalAmount() / billPlacedRequest.getMemberIdMap().size()) + 1)
								.build())
						.collect(Collectors.toList());
				// 沒被抽到要負擔餘數的人的 billDetail
				List<BillDetail> luckyList = billPlacedRequest.getMemberIdMap().entrySet().stream()
						.filter(x -> !unluckyMember.contains(x.getKey()))
						.map(x -> BillDetail.builder()
								.billId(billId).billDetailType(BillDetailType.EXPENSE)
								.memberId(x.getKey())
								.amount((bill.getTotalAmount() / billPlacedRequest.getMemberIdMap().size()))
								.build())
						.collect(Collectors.toList());
				// 將上面兩個 list merge 起來
				billDetailList.addAll(unluckyList);
				billDetailList.addAll(luckyList);

			}
			// 付錢的人的 billDetail
			billDetailList.add(BillDetail.builder()
					.billId(billId).billDetailType(BillDetailType.INCOME)
					.memberId(billPlacedRequest.getMemberId())
					.amount(billPlacedRequest.getTotalAmount())
					.build());
			// 新增 付錢的人的 billDetail 到 list 裡面
			billDetailService.addBillDetail(billDetailList);
			
			// 將BillDetailList轉型為BillAddRequest
			List<BillAddedRequest> billAddedRequest = billDetailList.stream()
					.map(x -> billMapper.billDetailToBillAddedRequest(x))
					.collect(Collectors.toList());
			// 利用OpenFeign抓updateBalance的API
			memberProxy.updateBalanceByAdd(billAddedRequest);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}


	@Override
	public boolean addBillGoDutch(BillPlacedRequest billPlacedRequest) {

		try {
			Bill bill = billRepository.save(billMapper.billPlacedRequestToBill(billPlacedRequest));
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
			
			// 將BillDetailList轉型為BillAddRequest
			List<BillAddedRequest> billAddedRequest = billDetailList.stream()
					.map(x -> billMapper.billDetailToBillAddedRequest(x))
					.collect(Collectors.toList());
			// 利用OpenFeign抓updateBalance的API
			memberProxy.updateBalanceByAdd(billAddedRequest);

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean updateBillTransfer(BillPlacedRequest billPlacedRequest) {

		try {
			
			Bill bill = billRepository.save(billMapper.billPlacedRequestToBill(billPlacedRequest));
			billRepository.flush();
			Long billId = bill.getBillId();
			
			otherBillServices.removeBill(billId);
			
			otherBillServices.addBillTransfer(billPlacedRequest);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}


	@Override
	public boolean updateBillAA(BillPlacedRequest billPlacedRequest) {

		try {
			
			Bill bill = billRepository.save(billMapper.billPlacedRequestToBill(billPlacedRequest));
			billRepository.flush();
			Long billId = bill.getBillId();
			
			otherBillServices.removeBill(billId);
			
			otherBillServices.addBillTransfer(billPlacedRequest);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public boolean updateBillGoDutch(BillPlacedRequest billPlacedRequest) {

		try {
			
			Bill bill = billRepository.save(billMapper.billPlacedRequestToBill(billPlacedRequest));
			billRepository.flush();
			Long billId = bill.getBillId();
			
			otherBillServices.removeBill(billId);
			
			otherBillServices.addBillTransfer(billPlacedRequest);
			
			

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	

	@Override
	public boolean removeBill(Long billId) {
		try {
			
			List<BillDetail> billDetailList = billDetailRepository.findByBillId(billId);
			
			Map<Long, Long> billDetailMapExpense = new HashMap<>();
			billDetailList.stream()
						.filter(x -> x.getBillDetailType() == BillDetailType.EXPENSE)
						.forEach(x -> billDetailMapExpense.put(x.getMemberId(), x.getAmount()));
			
			Map<Long, Long> billDetailMapImcome = new HashMap<>();
			billDetailList.stream()
						.filter(x -> x.getBillDetailType() == BillDetailType.INCOME)
						.forEach(x -> billDetailMapImcome.put(x.getMemberId(), x.getAmount()));
			
			// 將BillDetailList轉型為BillAddRequest
			List<BillAddedRequest> billAddedRequest = billDetailList.stream()
					.map(x -> billMapper.billDetailToBillAddedRequest(x))
					.collect(Collectors.toList());
			// 利用OpenFeign抓updateBalance的API
			memberProxy.updateBalanceByDelete(billAddedRequest);
						
			billDetailService.removeBillDetail(billId);
						
			billRepository.deleteById(billId);
									
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
