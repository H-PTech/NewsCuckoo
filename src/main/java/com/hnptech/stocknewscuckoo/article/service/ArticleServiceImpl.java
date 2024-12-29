package com.hnptech.stocknewscuckoo.article.service;

import com.hnptech.stocknewscuckoo.article.dto.response.ArticleResponse;
import com.hnptech.stocknewscuckoo.article.mapper.ArticleMapper;
import com.hnptech.stocknewscuckoo.article.model.Article;
import com.hnptech.stocknewscuckoo.article.repository.ArticleRepository;
import com.hnptech.stocknewscuckoo.events.article.ArticleEventPublisher;
import com.hnptech.stocknewscuckoo.events.crawler.CrawlerFetchedEvent;
import com.hnptech.stocknewscuckoo.translation.service.TranslationService;
import com.hnptech.stocknewscuckoo.utils.converter.service.TimeConverter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

	private final ArticleRepository articleRepository;
	private final ArticleMapper articleMapper;
	private final ArticleEventPublisher articleEventPublisher;
	private final TranslationService translationService;
	private final TimeConverter timeConverter;

	@Override
	public List<ArticleResponse> getLatestArticles() {
		return articleMapper.toResponseList(articleRepository.findTop20ByOrderByPublishedAtDesc());
	}

	@Override
	public void saveArticles(CrawlerFetchedEvent articles) {
		List<Article> notDuplicatedArticles = articles.articles().stream()
				.filter(article -> !articleRepository.existsById(article.getUrl()))
				.toList();

		for (Article article : notDuplicatedArticles) {
			article.updateTitle(translationService.translate(article.getTitle()));
			article.updatePublishedAt(
					timeConverter.convertUSTimeToKoreaTime(article.getPublishedAt()));
		}

		articleEventPublisher.publishArticleCreated(notDuplicatedArticles);
		articleRepository.saveAll(notDuplicatedArticles);
	}

}
