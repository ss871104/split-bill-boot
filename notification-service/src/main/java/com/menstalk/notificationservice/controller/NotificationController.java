package com.menstalk.notificationservice.controller;

import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menstalk.notificationservice.domain.NotificationVo;
import com.menstalk.notificationservice.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController  // 讓 spring 知道這個 class 是要交由 spring 去管理的，並且是 Controller (@ResponseBody + @Controller)
@RequestMapping("/api/notification") // api 命名
@RequiredArgsConstructor // 有參數建構子
@CrossOrigin // 前後端分離所需
public class NotificationController {
	
	private final NotificationService notificationService;
	
	@PostMapping("/newNotification")
	public ResponseEntity<Boolean> addNotification(NotificationVo notification) { // NotificationVo是我自己設的資料型態，VO裡的所有資料可以視為一種。
		if (notificationService.addNotification(notification)) {
			return new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
		}
	}
	
}
