package com.hnptech.stocknewscuckoo.notification.service;

import com.hnptech.stocknewscuckoo.crawler.model.Article;

public interface NotificationService {

	void sendNotification(Article article);
}
