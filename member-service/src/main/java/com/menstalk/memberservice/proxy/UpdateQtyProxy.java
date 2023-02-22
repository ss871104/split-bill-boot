package com.menstalk.memberservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("party-service")
public interface UpdateQtyProxy {

	@PutMapping("/updateQty/{partyId}")
	public boolean updateQty(@PathVariable("partyId") Long partyId,
			@RequestParam(value = "memberQty", required = true) Long memberQty);
}
