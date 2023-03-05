package com.menstalk.partyservice.proxy;

import com.menstalk.partyservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserProxy {

	@GetMapping("/api/user/{userId}")
	public UserResponse findUserByUserId(@PathVariable("userId") Long userId);

}
