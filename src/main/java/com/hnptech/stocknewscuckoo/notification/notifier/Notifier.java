package com.hnptech.stocknewscuckoo.notification.notifier;

import com.hnptech.stocknewscuckoo.notification.dto.response.NotificationItem;

public interface Notifier {

	void sendNotification(NotificationItem request);
}
