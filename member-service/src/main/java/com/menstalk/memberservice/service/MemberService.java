package com.menstalk.memberservice.service;

import java.util.List;

import com.menstalk.memberservice.domain.Member;
import com.menstalk.memberservice.dto.BillAddedRequest;
import com.menstalk.memberservice.dto.PartyResponse;

public interface MemberService {

	public List<Member> findMembersByPartyId(Long partyId);
	public List <PartyResponse> findPartysByUserId(Long userId);
	public boolean addMembers(Member member);
	public boolean updateMember(Member member);
	public boolean deleteMember(Long memberId);
	public boolean updateBalanceAdd(List<BillAddedRequest> list);
	public boolean updateBalanceDelete(List<BillAddedRequest> list);
}
