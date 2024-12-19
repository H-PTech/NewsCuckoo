package com.hnptech.stocknewscuckoo.notification.service;

import com.hnptech.stocknewscuckoo.article.model.Article;

public interface NotificationService {

	void sendNotification(Article article);
}
