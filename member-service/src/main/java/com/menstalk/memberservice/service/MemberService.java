package com.menstalk.memberservice.service;

import java.util.List;

import com.menstalk.memberservice.domain.Member;
import com.menstalk.memberservice.dto.BillAddedRequest;


public interface MemberService {
	public List<Long> findUserIdByPartyId(Long PartyId);
	public List<Long> findUserInPartysByUserId(Long userId);
	public List<Member> findMembersByPartyId(Long partyId);
	public boolean addMemberByCreateParty(Member member);
	public boolean addMembers(Member member);
	public boolean updateMember(Member member);
	public boolean deleteMemberByPartyId(Long partyId);
	public boolean deleteMemberById(Long memberId);
	public boolean updateBalanceAdd(List<BillAddedRequest> list);
	public boolean updateBalanceDelete(List<BillAddedRequest> list);
}
