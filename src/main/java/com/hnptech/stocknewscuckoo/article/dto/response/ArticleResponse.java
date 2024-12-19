package com.hnptech.stocknewscuckoo.article.dto.response;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleResponse {

	private String url;
	private String title;
	private LocalDateTime publishedAt;

	@Builder
	public ArticleResponse(String url, String title, LocalDateTime publishedAt) {
		this.url = url;
		this.title = title;
		this.publishedAt = publishedAt;
	}
}
