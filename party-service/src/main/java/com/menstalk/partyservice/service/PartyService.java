package com.menstalk.partyservice.service;

import java.util.List;

import com.menstalk.notificationservice.domain.NotificationVo;
import com.menstalk.notificationservice.dto.NotificationRequest;
import com.menstalk.partyservice.domain.Party;
import com.menstalk.partyservice.dto.CountMember;

public interface PartyService {
public	List<Party> selectByParty(Long partyId);

public boolean addParty(Party party);
public boolean deleteParty(Long partyId);
public boolean updateParty(Party party);
public Long countMemberQuantityByPartyId(Long partyId);
 

}
