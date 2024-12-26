package com.hnptech.stocknewscuckoo.token.dto;

import lombok.Builder;
import lombok.Getter;

public record DeviceTokenItem(String deviceToken) {

	@Builder
	public DeviceTokenItem {
	}
}
