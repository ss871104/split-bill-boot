package com.menstalk.billservice.mapper;

import com.menstalk.billservice.domain.Bill;
import com.menstalk.billservice.domain.BillDetail;
import com.menstalk.billservice.dto.BillAddedRequest;
import com.menstalk.billservice.dto.BillPlacedRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillMapper {
	BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);
	
	Bill billPlacedRequestToBill(BillPlacedRequest billPlacedRequest);
	
	BillAddedRequest billDetailToBillAddedRequest(BillDetail billDetail);

}
