package com.menstalk.billservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.menstalk.billservice.domain.Bill;
import com.menstalk.billservice.dto.BillPlacedRequest;

@Mapper
public interface BillMapper {
	BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);
	
	Bill billPlacedRequestToBill(BillPlacedRequest billPlacedRequest);

}
