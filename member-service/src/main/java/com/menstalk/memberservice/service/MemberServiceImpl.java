package com.menstalk.memberservice.service;

import org.springframework.stereotype.Service;

import com.menstalk.memberservice.domain.Member;
import com.menstalk.memberservice.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	@Override
	public boolean addMembers(Member member) {
		return false;
		
	}
	@Override
	public boolean updateMember(Member member) {
		return false;
			
	}
	@Override
	public boolean deleteMember(Long memberId) {
		return false;
			
	}

	
}
