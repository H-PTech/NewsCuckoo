package com.hnptech.stocknewscuckoo.article.service;

import com.hnptech.stocknewscuckoo.article.model.Article;
import com.hnptech.stocknewscuckoo.article.repository.ArticleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

	private final ArticleRepository articleRepository;

	@Override
	public List<Article> getLatestArticles() {
		return articleRepository.findTop20ByOrderByPublishedAtDesc();
	}


	//TODO : 중복된 기사가 저장됨 수정해야함
	@Override
	public void saveArticles(List<Article> articles) {
		List<Article> notDuplicatedArticles = articles.stream()
				.filter(article -> !articleRepository.existsById(article.getUrl()))
				.toList();

		articleRepository.saveAll(notDuplicatedArticles);
	}
}
