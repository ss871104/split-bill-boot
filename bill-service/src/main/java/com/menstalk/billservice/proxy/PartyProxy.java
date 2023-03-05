package com.menstalk.billservice.proxy;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("party-service")
public interface PartyProxy {
	@GetMapping("/api/party/findPartyNameByPartyId/{partyId}")
	String findPartyNameByPartyId(@PathVariable("partyId") Long partyId);
}
