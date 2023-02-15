package com.menstalk.notificationservice.domain;

public enum NotificationType {
	OWED_WEEKLY("Weekly Account Owed Notification", "You owed $%s in party %s , please clear your debt!"),
	INVITE("Invitation Notification", "Hi, kindly invite you to the party %s"),
	JOIN("New Member Join Notification", "Welcome the new member, %s"),
	BILL_ADD("Bill Added Notification", "New bill added in the party %s"),
	NEW_USER("Welcome New User Notification", "Welcome %s! Thanks for having you here :)");
	
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
