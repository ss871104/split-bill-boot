package com.menstalk.notificationservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("party-service")
public interface PartyProxy {
    @GetMapping("/api/party/findPartyNameByPartyId/{partyId}")
    public String findPartyNameByPartyId(@PathVariable("partyId") Long partyId);
}
