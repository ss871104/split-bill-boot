package com.menstalk.billservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("party-service")
public class PartyProxy {

}
