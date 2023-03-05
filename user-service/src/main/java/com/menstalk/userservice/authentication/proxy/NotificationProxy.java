package com.menstalk.userservice.authentication.proxy;

import com.menstalk.userservice.authentication.dto.NewUserRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notification-service")
public interface NotificationProxy {
    @PostMapping("/api/notification/addUserNotification")
    boolean addNewUserNotification(@RequestBody NewUserRequest newUserRequest);
}
