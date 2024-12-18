package com.hnptech.stocknewscuckoo.news.model;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News {

	private String title;

	private String content;

	private String url;

	private String publishedAt;


	@Builder
	public News(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
