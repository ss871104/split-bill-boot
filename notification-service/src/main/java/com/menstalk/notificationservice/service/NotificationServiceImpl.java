package com.menstalk.notificationservice.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.menstalk.notificationservice.domain.NotificationVo;
import com.menstalk.notificationservice.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service // 讓 spring 知道這個 class 是要交由 spring 去管理的，並且是 Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
	
	private final NotificationRepository notificationRepository; // 呼叫DAo(repository)
	
	@Override
	public boolean addNotification (NotificationVo notification) {
		// 現在要寫讓通知進到DAO再進到資料庫!!!
		// NotificationVo newNotificationVo = notificationRepository.save(notification);
		try {
			notificationRepository.save(notification);
			return true;
		} catch (Exception e) {
			e.printStackTrace();   //執行後若有錯誤印出在CONSOLE
			return false;
			
		} 
		
}

	@Override
	public List<NotificationVo> notificationVo() {
		notificationRepository.findAll();
		return null;
	}}
