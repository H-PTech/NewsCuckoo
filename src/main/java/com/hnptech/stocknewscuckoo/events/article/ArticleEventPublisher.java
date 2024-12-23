package com.hnptech.stocknewscuckoo.events.article;

import com.hnptech.stocknewscuckoo.article.model.Article;
import com.hnptech.stocknewscuckoo.events.common.EventPublisher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ArticleEventPublisher {

	private final EventPublisher eventPublisher;

	public void publishArticleCreated(List<Article> articles) {
		ArticleCreatedEvent event = new ArticleCreatedEvent(articles);
		eventPublisher.publish(event);
	}
}
