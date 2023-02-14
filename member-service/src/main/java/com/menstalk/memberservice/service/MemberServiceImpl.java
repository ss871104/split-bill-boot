package com.menstalk.memberservice.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.menstalk.memberservice.domain.Member;
import com.menstalk.memberservice.dto.BillAddedRequest;
import com.menstalk.memberservice.dto.BillDetailType;
import com.menstalk.memberservice.mapper.MemberMapper;
import com.menstalk.memberservice.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final MemberMapper memberMapper;

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
		}
		return false;

	}

	@Override
	public boolean updateMember(Member member) {
		if (memberRepository.existsById(member.getMemberId())) {
			memberRepository.save(member);
			return true;
		}
		return false;
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

	@Override
	public boolean updateBalanceAdd(List<BillAddedRequest> list) {
		//從request中抓下所有的memberId
		Set<Long> memberIds = list.stream().map(x -> x.getMemberId()).collect(Collectors.toSet());
		//拿那些memberId去查對應的全部資料
		List<Member> memberListOrigin =  memberRepository.findAllById(memberIds);
		//建一個map，key為memberId，value為對應到的member資料
		Map<Long, Member> memberMapOrigin = new HashMap<>();
		memberListOrigin.forEach(x -> memberMapOrigin.put(x.getMemberId(), x));
		//從REQUEST里如果BillDetailType是EXPENSE的話，從memberListOrigin中抓出原本的BALANCE去減掉REQUEST中對應到的MEMBERiD的amount，反之亦然
		list.forEach(x -> {
			if (x.getBillDetailType() == BillDetailType.EXPENSE) {
				memberMapOrigin.put(x.getMemberId(), memberMapOrigin.get(x.getMemberId())).setBalance(memberMapOrigin.get(x.getMemberId()).getBalance() - x.getAmount());
			} else {
				memberMapOrigin.put(x.getMemberId(), memberMapOrigin.get(x.getMemberId())).setBalance(memberMapOrigin.get(x.getMemberId()).getBalance() + x.getAmount());
			}
		});
		//把map的value(Member)轉成list
		List<Member> memberList = memberMapOrigin.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());
		
		memberRepository.saveAll(memberList);
		return false;
	}
}
