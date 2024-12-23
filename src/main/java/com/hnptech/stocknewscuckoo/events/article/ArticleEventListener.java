package com.hnptech.stocknewscuckoo.events.article;

import com.hnptech.stocknewscuckoo.notification.service.NotificationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ArticleEventListener {

	private final NotificationService notificationService;
	@Async
	@EventListener
	public void handleArticleCreatedEvent(@NonNull ArticleCreatedEvent event) {
		log.info("알림 보내기 성공");
		notificationService.sendNotification(event);
	}

}