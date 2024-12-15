package com.hnptech.stocknewscuckoo.notification.service;

import com.hnptech.stocknewscuckoo.notification.model.News;

public interface NotificationService {

	void sendNotification(News news);
}
