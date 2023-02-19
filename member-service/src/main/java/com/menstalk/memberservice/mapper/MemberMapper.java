package com.menstalk.memberservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
	MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

	

	// Member dtoToMember(BillAddedRequest billAddedRequest);
}