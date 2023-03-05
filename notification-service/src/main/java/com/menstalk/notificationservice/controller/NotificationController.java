package com.menstalk.notificationservice.controller;

import com.menstalk.notificationservice.dto.NewBillRequest;
import com.menstalk.notificationservice.dto.NewMemberRequest;
import com.menstalk.notificationservice.dto.NewUserRequest;
import com.menstalk.notificationservice.dto.NotificationResponse;
import com.menstalk.notificationservice.service.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/notification")
@RestController
@RequiredArgsConstructor
@Api(tags = "Notification Api")
public class NotificationController {

	private final NotificationService notificationService;

	@PostMapping("/addUserNotification")
	@ApiOperation("(Internal) Add notification when new user added")
	public boolean addNewUserNotification(@RequestBody NewUserRequest newUserRequest) {
		if (notificationService.addNewUserNotification(newUserRequest)) {
			return true;
		}
		return false;
	}

	@PostMapping("/addBillNotification")
	@ApiOperation("(Internal) Add notification when new bill added")
	public boolean addNewBillNotification(@RequestBody NewBillRequest newBillRequest) {
		if (notificationService.addNewBillNotification(newBillRequest)) {
			return true;
		}
		return false;
	}

	@PostMapping("/addMemberNotification")
	@ApiOperation("(Internal) Add notification when new member added")
	public boolean addNewMemberNotification(@RequestBody NewMemberRequest newMemberRequest) {
		if (notificationService.addNewMemberNotification(newMemberRequest)) {
			return true;
		}
		return false;
	}

	@GetMapping("/findByUserId/{userId}")
	@ApiOperation("(External) Find all notifications by userId")
	public ResponseEntity<List<NotificationResponse>> findByUserId(@PathVariable Long userId) {

		return new ResponseEntity<>(notificationService.findByUserId(userId), HttpStatus.OK);

	}

	@PutMapping("/updateStatus")
	@ApiOperation("(External) Update notification status to read by notificationId")
	public boolean updateStatusById(@RequestParam("notificationId") Long notificationId) {
		if (notificationService.updateStatus(notificationId)) {
			return true;
		}
		return false;

	}
}