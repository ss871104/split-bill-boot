package com.menstalk.partyservice.repository;

import com.menstalk.partyservice.domain.Party;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, Long> { // 結尾加上extends(繼承) Jpar 幫你實作方法

//	@Query("UPDATE Party SET memberQuantity = :memberQuantity WHERE partyId = :partyId")
//	boolean updateMemberQty(@Param("partyId") Long partyId, @Param("memberQuantity") Long memberQty);

}