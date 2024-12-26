package com.hnptech.stocknewscuckoo.notification.service;

import com.hnptech.stocknewscuckoo.events.article.ArticleCreatedEvent;
import com.hnptech.stocknewscuckoo.notification.dto.response.NotificationItem;
import com.hnptech.stocknewscuckoo.translation.service.TranslationService;
import com.hnptech.stocknewscuckoo.utils.converter.service.TimeConverter;
import com.hnptech.stocknewscuckoo.notification.notifier.Notifier;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final List<Notifier> notifiers;
	private final TimeConverter timeConverter;
	private final TranslationService translationService;


	// TODO : 개선사항 notifier 비동기 동작으로 개선가능
	@Override
	public void sendNotification(@NonNull ArticleCreatedEvent event) {
		event.articles().stream()
				.map(article -> NotificationItem.builder()
						.url(article.getUrl())
						.publishedAt(timeConverter.convertUSTimeToKoreaTime(article.getPublishedAt()))
						.title(translationService.translate(article.getTitle()))
						.build()
				)
				.forEach(notification ->
						notifiers.forEach(notifier -> notifier.sendNotification(notification))
				);
	}
}
