package com.menstalk.memberservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.menstalk.memberservice.domain.Member;
import com.menstalk.memberservice.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;

	@Override
	public List<Member> selectByPartyId(Long partyId) {
		List<Member> Memberlist = new ArrayList<>();
		Memberlist = memberRepository.findByPartyId(partyId);
		return Memberlist;
	}

	@Override
	public boolean addMembers(Member member) {
		if (member.getMemberId() == null) {
			Member newMember = memberRepository.save(member);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean updateMember(Member member) {
		try {
			memberRepository.save(member);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteMember(Long memberId) {
		try {
			memberRepository.deleteById(memberId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
