package com.hnptech.stocknewscuckoo.notification.dto.request;


import java.time.LocalDateTime;
import lombok.Builder;


public record NotificationRequest(String title, LocalDateTime publishedAt, String url) {

	@Builder
	public NotificationRequest {
	}

	@Override
	public String toString() {
		return "NotificationRequest {\n" +
				"  Title: " + title + "\n" +
				"  Published At: " + publishedAt + "\n" +
				"  URL: " + url + "\n" +
				"}";
	}
}
