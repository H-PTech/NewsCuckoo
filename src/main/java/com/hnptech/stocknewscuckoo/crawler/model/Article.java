package com.hnptech.stocknewscuckoo.crawler.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String url;

	// 미국 시간 기준
	private String publishedAt;

	@Builder
	public Article(String title, String url, String publishedAt) {
		this.title = title;
		this.url = url;
		this.publishedAt = publishedAt;
	}
}
