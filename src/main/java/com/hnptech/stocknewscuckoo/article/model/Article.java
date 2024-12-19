package com.hnptech.stocknewscuckoo.article.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article {

	@Id
	private String url;

	private String title;

	// 미국 시간 기준
	private LocalDateTime publishedAt;

	@Builder
	public Article(String title, String url, LocalDateTime publishedAt) {
		this.title = title;
		this.url = url;
		this.publishedAt = publishedAt;
	}

}
