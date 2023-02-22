package com.menstalk.memberservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.menstalk.memberservice.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	List<Member> findMemberIdsByPartyId(Long partyId);

	@Query("SELECT DISTINCT m.partyId FROM Member m WHERE m.userId = :userId")
	List<Long> findUserInPartysByUserId(Long partyId);

	@Query("SELECT COUNT(m) FROM Member m WHERE m.partyId = :partyId")
	Long countMember(@Param("partyId") Long partyId);

}
