package com.menstalk.notificationservice.controller;

import com.menstalk.notificationservice.dto.NotificationRequest;
import com.menstalk.notificationservice.dto.NotificationResponse;
import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.menstalk.notificationservice.domain.NotificationVo;
import com.menstalk.notificationservice.service.NotificationService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RestController // 讓 spring 知道這個 class 是要交由 spring 去管理的，並且是 Controller (@ResponseBody +
				// @Controller)
@RequestMapping("/api/notification") // api 命名
@RequiredArgsConstructor // 有參數建構子
//@CrossOrigin // 前後端分離所需
public class NotificationController {

	private final NotificationService notificationService;

	@PostMapping("/addInvitation")
	public ResponseEntity<Boolean> addInvitationNotification(@RequestBody NotificationRequest notificationRequest) { // NotificationVo是我自己設的資料型態，VO裡的所有資料可以視為一種。
		if (notificationService.addInvitationNotification(notificationRequest)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findAll")
	public ResponseEntity<List<NotificationResponse>> findAllNotification() {
		List<NotificationVo> list = notificationService.notificationVo();
		List<NotificationResponse> reponseList = list.stream()
				.map(x -> NotificationResponse.builder().notificationId(x.getNotificationId()).userId(x.getUserId())
						.status(x.getStatus()).createTime(x.getCreateTime()).title(x.getTitle()).content(x.getContent())
						.build())
				.collect(Collectors.toList());
		return new ResponseEntity<>(reponseList, HttpStatus.OK);
	}

	@PostMapping("/addJoin")
	public ResponseEntity<Boolean> addJoinNotification(@RequestBody NotificationRequest notificationRequest) { // NotificationVo是我自己設的資料型態，VO裡的所有資料可以視為一種。
		if (notificationService.addJoinNotification(notificationRequest)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/newuser")
		public ResponseEntity<Boolean> addnewuserNotification(@RequestBody NotificationRequest notificationRequest) { // NotificationVo是我自己設的資料型態，VO裡的所有資料可以視為一種。
			if (notificationService.addNewuserNotification(notificationRequest)) {
				return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
				}}

	@PostMapping("/billadd")
			public ResponseEntity<Boolean> addbilladdNotification(@RequestBody NotificationRequest notificationRequest) { // NotificationVo是我自己設的資料型態，VO裡的所有資料可以視為一種。
				if (notificationService.addNewuserNotification(notificationRequest)) {
					return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
				} else {
					return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);	
		}
}}