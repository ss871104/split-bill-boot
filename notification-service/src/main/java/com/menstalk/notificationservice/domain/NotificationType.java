package com.menstalk.notificationservice.domain;

public enum NotificationType {
	OWED_WEEKLY("Weekly Account Owed Notification", ""),
	INVITE("Invitation Notification", "Hi, kindly invite you to the party ${partyName}"),
	JOIN("New Member Join Notification", ""),
	BILL_ADD("Bill Added Notification", ""),
	NEW_USER("Welcome New User Notification", "");
	
	private String title;
	private String content;
	
	NotificationType(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
