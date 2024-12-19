package com.hnptech.stocknewscuckoo.article.mapper;

import com.hnptech.stocknewscuckoo.article.dto.response.ArticleResponse;
import com.hnptech.stocknewscuckoo.article.model.Article;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapperImpl implements ArticleMapper {

	@Override
	public ArticleResponse toResponse(Article article) {
		return ArticleResponse.builder()
				.url(article.getUrl())
				.title(article.getTitle())
				.publishedAt(article.getPublishedAt())
				.build();
	}

	@Override
	public List<ArticleResponse> toResponseList(List<Article> articles) {
		return articles.stream()
				.map(this::toResponse) // 개별 매핑 메서드 호출
				.toList();
	}
}
