package com.menstalk.memberservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.menstalk.memberservice.domain.Member;
import com.menstalk.memberservice.dto.BillAddedRequest;

@Mapper
public interface MemberMapper {
	MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

	

	// Member dtoToMember(BillAddedRequest billAddedRequest);
}