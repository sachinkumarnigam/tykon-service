package com.tykon.notification.entity.request;

public enum EventType {

	EVENT(10), MESSAGE_CREATE(11), ATTENDANCE(12), ALBUM_CREATE(40), ALBUM_EDIT(41), 
	CONSENT_INVITATION(45),DAYCARE_CREATE(50);

	public int value;

	private EventType(int value) {
		this.value = value;
	}
}