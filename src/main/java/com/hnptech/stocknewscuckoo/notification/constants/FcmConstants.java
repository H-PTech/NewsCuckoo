package com.hnptech.stocknewscuckoo.notification.constants;

import lombok.Getter;

@Getter
public enum FcmConstants {

	DEFAULT_SOUND("default");

	private final String value;

	FcmConstants(String value) {
		this.value = value;
	}
}
