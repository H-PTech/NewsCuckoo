package com.hnptech.stocknewscuckoo.article.service;

import com.hnptech.stocknewscuckoo.article.dto.response.ArticleResponse;
import com.hnptech.stocknewscuckoo.article.mapper.ArticleMapper;
import com.hnptech.stocknewscuckoo.article.model.Article;
import com.hnptech.stocknewscuckoo.article.repository.ArticleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

	private final ArticleRepository articleRepository;
	private final ArticleMapper articleMapper;
	@Override
	public List<ArticleResponse> getLatestArticles() {
		 return articleMapper.toResponseList(articleRepository.findTop20ByOrderByPublishedAtDesc());
	}

	@Override
	public void saveArticles(List<Article> articles) {
		List<Article> notDuplicatedArticles = articles.stream()
				.filter(article -> !articleRepository.existsById(article.getUrl()))
				.toList();

		articleRepository.saveAll(notDuplicatedArticles);
	}

	//TODO : 중복 기사 데이터 확인 최적화, 상수 관리, Get 쿼리 최적화
}
