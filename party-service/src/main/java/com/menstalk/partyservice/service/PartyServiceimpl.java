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
import com.menstalk.partyservice.memberProxy.CountMemberProxy;
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
		
			partyRepository.save(party);
			return true;
	}

	@Override
	public boolean deleteParty(Long partyId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateParty(Party partyVo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long countMemberQuantityByPartyId(Long partyId) {
		
		return countMemberProxy.countMember(partyId);
	}


	}
