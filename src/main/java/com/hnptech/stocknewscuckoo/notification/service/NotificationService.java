package com.hnptech.stocknewscuckoo.notification.service;

import com.hnptech.stocknewscuckoo.news.model.News;

public interface NotificationService {

	void sendNotification(News news);
}
