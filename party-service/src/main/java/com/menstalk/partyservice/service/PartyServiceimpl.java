package com.menstalk.partyservice.service;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.menstalk.partyservice.domain.Party;
import com.menstalk.partyservice.dto.Member;
import com.menstalk.partyservice.dto.MemberStatus;
import com.menstalk.partyservice.mapper.PartyMapper;
import com.menstalk.partyservice.proxy.MemberProxy;
import com.menstalk.partyservice.proxy.UserProxy;
import com.menstalk.partyservice.repository.PartyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PartyServiceimpl implements PartyService {

	private final PartyRepository partyRepository;
	private final MemberProxy memberProxy;
	private final PartyMapper partyMapper;
	private final UserProxy userProxy;

	@Override
	public List<Party> selectByParty(Long partyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addParty(Party party, Long userId) {
		if (party.getPartyId() == null) {
			party.setMemberQuantity(1L);
			party = partyRepository.save(party);
			partyRepository.flush(); // 確保目前在應用程式中做出的修改都已經被保存到資料庫中。
			String name = userProxy.findUserByUserId(userId).getName();
			Long partyId = party.getPartyId();
			Member member = Member.builder().memberNickname(name).userId(userId).partyId(partyId)
					.createTime(LocalDateTime.now()).memberStatus(MemberStatus.JOINED).build();
			memberProxy.addMemberByCreateParty(member);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteParty(Long partyId) {
		try {

			memberProxy.deleteAllByPartyId(partyId);
			partyRepository.deleteById(partyId);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateParty(Long partyId, String partyName) {
		try {
			Party party = new Party();
			party = partyRepository.findById(partyId).orElseThrow();
			party.setPartyName(partyName);
			partyRepository.save(party);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateMemberQty(Long partyId, Long memberQty) {

		try {
			Party party = new Party();
			party = partyRepository.findById(partyId).orElseThrow();
			party.setMemberQuantity(memberQty);
			partyRepository.save(party);
//			partyRepository.updateMemberQty(partyId, memberQty);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Party> findPartysByPartyIds(Long userId) {
		List<Long> partyIdList = memberProxy.findUserInPartysByUserId(userId);
		List<Party> partyList = partyRepository.findAllById(partyIdList);
//		List<Party> partyList = new ArrayList<>();
//		for (int i = 0; i < partyIdList.size(); i++) {
//			Long partyId = partyIdList.get(i);
//		Party party = partyRepository.findById(partyId).orElseThrow(); // 這裡是把partyid丟到Partyrespository的.findById()方法 去找出PARTY的所有資料再放到PARTY類別 裡面。
//			if (party != null) {
//				partyList.add(party);
//			}
//		}
		return partyList;

	}

	@Override
	public String findPartyNameByPartyId(Long partyId) {
		return partyRepository.findById(partyId).orElseThrow().getPartyName();
	}
}
