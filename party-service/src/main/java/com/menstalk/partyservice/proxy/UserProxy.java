package com.menstalk.partyservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.menstalk.partyservice.dto.UserResponse;

@FeignClient("user-service")
public interface UserProxy {

	@GetMapping("/api/user/{userId}")
	public UserResponse findUserByUserId(@PathVariable("userId") Long userId);

}
