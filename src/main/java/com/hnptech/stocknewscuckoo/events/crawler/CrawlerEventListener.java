package com.hnptech.stocknewscuckoo.events.crawler;

import com.hnptech.stocknewscuckoo.article.service.ArticleService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CrawlerEventListener {

	private final ArticleService articleService;

	@EventListener
	@Async
	public void handleFetchedArticles(@NonNull CrawlerFetchedEvent event) {
		articleService.saveArticles(event);
	}
}
