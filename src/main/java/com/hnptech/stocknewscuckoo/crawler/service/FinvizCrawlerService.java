package com.hnptech.stocknewscuckoo.crawler.service;

import static com.hnptech.stocknewscuckoo.crawler.constants.NewsSource.FINVIZ_MARKET;
import static com.hnptech.stocknewscuckoo.crawler.constants.NewsSource.FINVIZ_STOCK;

import com.hnptech.stocknewscuckoo.article.model.Article;
import com.hnptech.stocknewscuckoo.article.service.ArticleService;
import com.hnptech.stocknewscuckoo.utils.converter.constants.TimeZones;
import com.hnptech.stocknewscuckoo.utils.converter.service.TimeConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FinvizCrawlerService implements NewsCrawlerService {

	private final ArticleService articleService;
	private final TimeConverter timeConverter;

	@Override
	@Scheduled(fixedRate = 60000)
	public void crawlLatestStockNews() {
		crawlNews(FINVIZ_STOCK.getUrl(), FINVIZ_STOCK.getCategory());
	}

	@Override
//	@Scheduled(fixedRate = 60000)
	public void crawlLatestMarketNews() {
		crawlNews(FINVIZ_MARKET.getUrl(), FINVIZ_MARKET.getCategory());
	}

	private void crawlNews(String url, String newsType) {
		log.info("{} 뉴스 크롤링 시작", newsType);
		try {
			Document document = Jsoup.connect(url).get();
			List<Article> articles = extractArticles(document);

			articles.forEach(article -> {
				log.info("제목: {} | 발행 시간: {} | URL: {}", article.getTitle(), article.getPublishedAt(), article.getUrl());
			});

			articleService.saveArticles(articles);

		} catch (Exception e) {
			log.error("Finviz {} 뉴스페이지 Fetch 실패", newsType, e);
		}
	}

	private List<Article> extractArticles(Document document) {
		Elements rows = document.select("tr.styled-row");
		List<Article> articles = new ArrayList<>();

		for (Element row : rows) {
			try {
				Article article = extractArticleFromRow(row);
				if (article != null) {
					articles.add(article);
				}
			} catch (Exception e) {
				log.warn("기사 추출 실패", e);
			}
		}

		return articles;
	}

	private Article extractArticleFromRow(Element row) {
		String title = row.selectFirst("td.news_link-cell a").text();
		String publishedAt = row.select("td.news_date-cell").text();
		String url = row.select("td.news_link-cell a").attr("abs:href");

		if (title.isEmpty() || publishedAt.isEmpty() || url.isEmpty()) {
			return null;
		}

		LocalDateTime articleDateTime = parsePublishedAt(publishedAt);
		if (articleDateTime == null) {
			return null; // 발행 시간이 유효하지 않으면 무시
		}

		// 오늘 날짜의 기사만 반환
		if (isToday(articleDateTime)) {
			return Article.builder()
					.title(title)
					.url(url)
					.publishedAt(articleDateTime)
					.build();
		}

		return null;
	}

	private LocalDateTime parsePublishedAt(String publishedAt) {
		try {
			if (isRelativeTimeFormat(publishedAt)) {
				return timeConverter.convertUsRelativeTimeToAbsoluteTime(publishedAt);
			}

			if (isAbsoluteTimeFormat(publishedAt)) {
				return timeConverter.usTimeToFormattedUSDate(publishedAt);
			}
		} catch (Exception e) {
			log.warn("발행 시간 파싱 실패: {}", publishedAt, e);
		}

		return null;
	}

	private boolean isRelativeTimeFormat(String time) {
		return time.matches("\\d+\\s+(min|hour|day)");
	}

	private boolean isAbsoluteTimeFormat(String time) {
		return time.matches("\\d{1,2}:\\d{2}\\s*(AM|PM)");
	}

	private boolean isToday(LocalDateTime dateTime) {
		return dateTime.toLocalDate().equals(LocalDate.now(ZoneId.of(TimeZones.US.getZoneId())));
	}
}
