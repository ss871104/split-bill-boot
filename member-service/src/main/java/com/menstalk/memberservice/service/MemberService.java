package com.menstalk.memberservice.service;

import com.menstalk.memberservice.domain.Member;

public interface MemberService {

	//public List<Member> selectByPartyId(Long partyId);
	public boolean addMembers(Member member);
	public boolean updateMember(Member member);
	public boolean deleteMember(Long memberId);

}
