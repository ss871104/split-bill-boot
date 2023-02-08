package com.menstalk.billservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.menstalk.billservice.domain.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{
	
	List<Bill> findByPartyId(Long partyId);

}
