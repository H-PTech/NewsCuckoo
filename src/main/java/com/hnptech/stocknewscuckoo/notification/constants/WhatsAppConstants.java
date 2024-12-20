package com.hnptech.stocknewscuckoo.notification.constants;

import lombok.Getter;

@Getter
public enum WhatsAppConstants {

	VERSION("v21.0"),
	URL("https://graph.facebook.com/%s/%s/message"),
	PHONE_NUMBER_ID("sample"),
	RECIPIENT_PHONE_NUMBER("recipient-phone-number"),
	MESSAGE_CONTENT("text-message-content");

	private final String value;

	WhatsAppConstants(String value) {
		this.value = value;
	}

}
