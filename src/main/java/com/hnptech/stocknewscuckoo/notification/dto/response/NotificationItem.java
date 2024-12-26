package com.hnptech.stocknewscuckoo.notification.dto.response;


import java.time.LocalDateTime;
import lombok.Builder;

public record NotificationItem(String url, String title, LocalDateTime publishedAt) {

	@Builder
	public NotificationItem {
	}
	@Override
	public String toString() {
		return String.format(" %s\n 발행시간(한국): %s\n  %s\n",
				title, publishedAt, url);
	}
}
