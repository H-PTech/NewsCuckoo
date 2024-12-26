package com.hnptech.stocknewscuckoo.notification.service;

import com.hnptech.stocknewscuckoo.events.article.ArticleCreatedEvent;

public interface NotificationService {

	void sendNotification(ArticleCreatedEvent event);
}
