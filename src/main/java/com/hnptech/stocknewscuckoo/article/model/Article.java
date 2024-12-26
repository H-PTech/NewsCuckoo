package com.hnptech.stocknewscuckoo.article.model;


import com.hnptech.stocknewscuckoo.article.constants.ArticleCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@ManyToOne
	@JoinColumn(name = "category_id") // 외래 키 이름 설정
	private Category category;


	@Builder
	public Article(String title, String url, LocalDateTime publishedAt, Category category) {
		this.title = title;
		this.url = url;
		this.publishedAt = publishedAt;
		this.category = category;
	}
}
