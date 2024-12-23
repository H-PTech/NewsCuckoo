package com.hnptech.stocknewscuckoo.events.crawler;

import com.hnptech.stocknewscuckoo.article.model.Article;
import com.hnptech.stocknewscuckoo.events.common.EventPublisher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CrawlerEventPublisher {

	private final EventPublisher eventPublisher;

	public void publishFetchedEvent(List<Article> articles) {
		CrawlerFetchedEvent event = new CrawlerFetchedEvent(articles);
		eventPublisher.publish(event);
	}
}
