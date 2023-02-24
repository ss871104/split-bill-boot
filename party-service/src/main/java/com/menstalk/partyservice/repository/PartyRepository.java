package com.menstalk.partyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.menstalk.partyservice.domain.Party;

public interface PartyRepository extends JpaRepository<Party, Long> { // 結尾加上extends(繼承) Jpar 幫你實作方法

//	@Query("UPDATE Party SET memberQuantity = :memberQuantity WHERE partyId = :partyId")
//	boolean updateMemberQty(@Param("partyId") Long partyId, @Param("memberQuantity") Long memberQty);
	public String findPartyNameByPartyId(Long partyId);

}