package com.hnptech.stocknewscuckoo.notification.service;

import com.hnptech.stocknewscuckoo.utils.converter.service.TimeConverterService;
import com.hnptech.stocknewscuckoo.crawler.model.Article;
import com.hnptech.stocknewscuckoo.notification.model.response.ConvertedNews;
import com.hnptech.stocknewscuckoo.notification.notifier.Notifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final Notifier notifier;
	private final TimeConverterService timeConverter;

	// TODO : 한글 제목 변환
	@Override
	public void sendNotification(Article article) {
		ConvertedNews convertedNews = ConvertedNews.builder()
				.url(article.getUrl())
				.koreanTime(timeConverter.convertUSTimeToKoreaTime(
						article.getPublishedAt()))
				.koreanTitle("TODO")
				.build();

		notifier.sendNotification(convertedNews.toString());
	}
}
