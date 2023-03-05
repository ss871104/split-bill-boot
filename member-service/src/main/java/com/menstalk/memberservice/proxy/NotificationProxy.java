package com.menstalk.memberservice.proxy;

import com.menstalk.memberservice.dto.NewMemberRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notification-service")
public interface NotificationProxy {
    @PostMapping("/api/notification/addMemberNotification")
    boolean addNewMemberNotification(@RequestBody NewMemberRequest newMemberRequest);
}