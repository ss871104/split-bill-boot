package com.menstalk.memberservice.service;

import java.util.List;

import com.menstalk.memberservice.domin.Member;

public interface MemberService {

	//public List<Member> selectByPartyId(Long partyId);
	public boolean addMembers(Member member);
	public boolean updateMember(Member member);
	public boolean deleteMember(Long memberId);

}
