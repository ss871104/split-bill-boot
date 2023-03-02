package com.menstalk.memberservice.proxy;

import com.menstalk.memberservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserProxy {

	@GetMapping("/api/user/{userId}")
	public UserResponse findUserByUserId(@PathVariable("userId") Long userId);

}
