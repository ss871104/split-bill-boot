package com.menstalk.memberservice.service;

import java.util.List;

import com.menstalk.memberservice.domain.Member;
import com.menstalk.memberservice.dto.BillAddedRequest;


public interface MemberService {
	public List<Long> findUserInPartysByUserId(Long userId);
	public List<Member> findMembersByPartyId(Long partyId);
	public boolean addMembers(Member member);
	public boolean updateMember(Member member);
	public boolean deleteMemberById(Long memberId);
	public boolean updateBalanceAdd(List<BillAddedRequest> list);
	public boolean updateBalanceDelete(List<BillAddedRequest> list);
	
}
