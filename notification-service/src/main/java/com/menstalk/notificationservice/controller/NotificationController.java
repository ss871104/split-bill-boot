package com.menstalk.notificationservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menstalk.notificationservice.dto.NewBillRequest;
import com.menstalk.notificationservice.dto.NewMemberRequest;
import com.menstalk.notificationservice.dto.NewUserRequest;
import com.menstalk.notificationservice.dto.NotificationResponse;
import com.menstalk.notificationservice.service.NotificationService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/notification")
@RestController
@RequiredArgsConstructor
@Api(tags = "Notification Api")
public class NotificationController {

	private final NotificationService NotificationService;

	@PostMapping("/addUserNotification")
	public boolean addNewUserNotification(@RequestBody NewUserRequest newUserRequest) {
		if (NotificationService.addNewUserNotification(newUserRequest)) {
			return true;
		}
		return false;
	}

	@PostMapping("/addBillNotification")
	public boolean addNewBillNotification(@RequestBody NewBillRequest newBillRequest) {
		if (NotificationService.addNewBillNotification(newBillRequest)) {
			return true;
		}
		return false;
	}

	@PostMapping("/addMemberNotification")
	public boolean addNewmemberNotification(@RequestBody NewMemberRequest newMemberRequest) {
		if (NotificationService.addNewMemberNotification(newMemberRequest)) {
			return true;
		}
		return false;
	}

	@GetMapping("/findByUserId/{userId}")
	public List<NotificationResponse> findByUserId(@PathVariable Long userId) {

		return NotificationService.findByUserId(userId);

	}
}