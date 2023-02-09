package com.menstalk.billservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.menstalk.billservice.domain.BillDetail;

public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
	
	List<BillDetail> findByBillId(Long billId);
	
	

}
