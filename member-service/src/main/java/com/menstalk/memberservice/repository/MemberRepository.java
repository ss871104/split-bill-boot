package com.menstalk.memberservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.menstalk.memberservice.domain.Member;
import com.menstalk.memberservice.dto.PartyResponse;

import feign.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

	List<Member> findMemberIdsByPartyId(Long partyId);

	// JPQL查询
	//@Query("SELECT p.id as partyId, u.id as userId, p.name as pname, COUNT(m.id) as memberQuantity, p.createTime as createTime "
	//+ "FROM Party p INNER JOIN p.users u LEFT JOIN p.members m WHERE u.id = :userId GROUP BY p.id")
	//本机SQL查询
	//@Query(value = "SELECT * FROM party WHERE id = :id", nativeQuery = true)

	List<PartyResponse> findPartysByUserId(@Param("userId") Long userId);

}
