package com.menstalk.partyservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.menstalk.partyservice.domain.Party;
import com.menstalk.partyservice.dto.CountMember;

@Mapper
public interface PartyMapper {
	
	PartyMapper INSTANCE = Mappers.getMapper(PartyMapper.class);
		
	CountMember memberQuantitytoCountMember(Party party);

}
