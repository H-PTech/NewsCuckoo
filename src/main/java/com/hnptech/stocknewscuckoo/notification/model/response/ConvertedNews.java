package com.hnptech.stocknewscuckoo.notification.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConvertedNews {

	private String koreanTitle;
	private String koreanTime;
	private String url;

	@Builder
	public ConvertedNews(String koreanTitle, String koreanTime, String url) {
		this.koreanTitle = koreanTitle;
		this.koreanTime = koreanTime;
		this.url = url;
	}


	@Override
	public String toString() {
		return "제목: " + koreanTitle + "\n" +
				"발행 시간: " + koreanTime + "\n" +
				"Link: " + url;
	}

}
