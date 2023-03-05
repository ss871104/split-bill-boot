package com.menstalk.billservice.repository;

import com.menstalk.billservice.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long>{
	
	List<Bill> findByPartyId(Long partyId);
	
//	@Query("UPDATE Bill SET partyId = :partyId, memberId = :memberId, "
//			+ "billName = :billName, type = :type, totalAmount = :totalAmount, createTime = :createTime "
//			+ "WHERE billId = :billId")
//	void updateBill(@Param(value = "billId") Long billId);

}
