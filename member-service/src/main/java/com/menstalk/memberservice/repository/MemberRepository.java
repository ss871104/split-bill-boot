package com.menstalk.memberservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.menstalk.memberservice.domain.Member;
import com.menstalk.memberservice.dto.PartyResponse;

public interface MemberRepository extends JpaRepository<Member, Long> {

	List<Member> findByPartyId(Long partyId);
	
	List<PartyResponse> findPartyIdsByUserId(Long userId);
}
