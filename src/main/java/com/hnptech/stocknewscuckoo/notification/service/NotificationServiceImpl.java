package com.hnptech.stocknewscuckoo.notification.service;

import com.hnptech.stocknewscuckoo.notification.converter.service.TimeConverterService;
import com.hnptech.stocknewscuckoo.notification.model.News;
import com.hnptech.stocknewscuckoo.notification.model.response.ConvertedNews;
import com.hnptech.stocknewscuckoo.notification.model.response.ConvertedNews.ConvertedNewsBuilder;
import com.hnptech.stocknewscuckoo.notification.notifier.Notifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final Notifier notifier;
	private final TimeConverterService timeConverter;

	@Override
	public void sendNotification(News news) {
		ConvertedNews convertedNews = ConvertedNews.builder()
				.url(news.getUrl())
				.koreanTime(timeConverter.convertUSTimeToKoreaTime(
						news.getPublishedAt()))
				.koreanTitle("TODO")
				.build();

		notifier.sendNotification(convertedNews.toString());

	}
}
