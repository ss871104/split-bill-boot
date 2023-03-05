package com.menstalk.billservice.repository;

import com.menstalk.billservice.domain.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
	
	List<BillDetail> findByBillId(Long billId);
	
	void deleteAllByBillId(Long billId);
	
}
