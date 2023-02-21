package com.menstalk.partyservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.menstalk.billservice.dto.BillAddedRequest;
import com.menstalk.billservice.proxy.MemberProxy;
import com.menstalk.partyservice.domain.Party;
import com.menstalk.partyservice.dto.CountMember;
import com.menstalk.partyservice.mapper.PartyMapper;
import com.menstalk.partyservice.proxy.CountMemberProxy;
import com.menstalk.partyservice.repository.PartyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PartyServiceimpl implements PartyService {

	private final PartyRepository partyRepository;
	private final CountMemberProxy countMemberProxy;
	private final PartyMapper partyMapper;

	@Override
	public List<Party> selectByParty(Long partyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addParty(Party party) {
		if (party.getPartyId() == null) {
			party.setMemberQuantity(1L);
			partyRepository.save(party);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteParty(Long partyId) {
		try {
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
}
	
