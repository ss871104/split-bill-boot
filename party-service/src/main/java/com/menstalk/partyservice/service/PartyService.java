package com.menstalk.partyservice.service;

import com.menstalk.partyservice.domain.Party;

import java.util.List;

public interface PartyService {
	public List<Party> selectByParty(Long partyId);

	public boolean addParty(Party party, Long userId);

	public boolean deleteParty(Long partyId);

	public boolean updateParty(Long partyId, String partyName);

	public boolean updateMemberQty(Long partyId, Long memberQty);

	public List<Party> findPartysByPartyIds(Long userId); // public(修飾字)後面是要傳送的值的資料型態 
	 
	public String findPartyNameByPartyId(Long partyId);
}
