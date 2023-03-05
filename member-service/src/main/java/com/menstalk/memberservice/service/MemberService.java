package com.menstalk.memberservice.service;

import com.menstalk.memberservice.domain.Member;
import com.menstalk.memberservice.dto.AddMemberRequest;
import com.menstalk.memberservice.dto.BillAddedRequest;

import java.util.List;


public interface MemberService {
	public List<Long> findUserIdByPartyId(Long PartyId);
	public List<Long> findUserInPartysByUserId(Long userId);
	public List<Member> findMembersByPartyId(Long partyId);
	public boolean addMemberByCreateParty(Member member);
	public boolean addMembers(AddMemberRequest addMemberRequest);
	public boolean updateStatus(Long memberId);
	public boolean deleteMemberByPartyId(Long partyId);
	public boolean deleteMemberById(Long memberId);
	public boolean updateBalanceAdd(List<BillAddedRequest> list);
	public boolean updateBalanceDelete(List<BillAddedRequest> list);
	List<Member> getAllMembers();
}
