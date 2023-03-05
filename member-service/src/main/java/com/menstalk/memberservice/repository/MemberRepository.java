package com.menstalk.memberservice.repository;

import com.menstalk.memberservice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
	@Query("SELECT m FROM Member m WHERE m.partyId = :partyId")
	List<Member> findMembersByPartyId(Long partyId);

	List<Long> findUserIdByPartyId(Long partyId);

	List<Member> findAllByPartyId(Long partyId);

	@Query("SELECT DISTINCT m.partyId FROM Member m WHERE m.userId = :userId")
	List<Long> findUserInPartysByUserId(@Param("userId")  Long userId);

	@Query("SELECT COUNT(m) FROM Member m WHERE m.partyId = :partyId")
	Long countMember(@Param("partyId") Long partyId);
	
//	@Query("DELETE FROM Member m WHERE m.partyId = :partyId")
	void deleteAllByPartyId(Long partyId);

	@Query("select m.userId from Member m where m.partyId = :partyId and m.memberStatus = 1")
	List<Long> findUserIdsByPartyIdWhereStatusIdJoined(@Param("partyId") Long partyId);

}
