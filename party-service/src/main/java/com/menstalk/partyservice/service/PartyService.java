package com.menstalk.partyservice.service;

import java.util.List;

import com.menstalk.partyservice.domain.Party;

public interface PartyService {
	public List<Party> selectByParty(Long partyId);

	public boolean addParty(Party party, Long userId);

	public boolean deleteParty(Long partyId);

	public boolean updateParty(Long partyId, String partyName);

	public boolean updateMemberQty(Long partyId, Long memberQty);

	public List<Party> findPartyByUserId(Long userId);

}
