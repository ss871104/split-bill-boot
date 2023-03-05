package com.menstalk.billservice.proxy;

import com.menstalk.billservice.dto.NewBillRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("notification-service")
public interface NotificationProxy {
    @PostMapping("/api/notification/addBillNotification")
    boolean addNewBillNotification(@RequestBody NewBillRequest newBillRequest);
}
