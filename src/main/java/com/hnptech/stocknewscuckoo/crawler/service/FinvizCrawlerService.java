package com.hnptech.stocknewscuckoo.crawler.service;

import static com.hnptech.stocknewscuckoo.crawler.constants.NewsSource.FINVIZ;

import com.hnptech.stocknewscuckoo.article.model.Article;
import com.hnptech.stocknewscuckoo.article.repository.ArticleRepository;
import com.hnptech.stocknewscuckoo.article.service.ArticleService;
import com.hnptech.stocknewscuckoo.utils.converter.service.TimeConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public void crawlLatestNews() {
		log.info("크롤링 시작");

		try {
			Document document = Jsoup.connect(FINVIZ.getUrl()).get();
			List<Article> articles = extractArticles(document);

			articles.forEach(article -> {
				log.info("제목: {} | 발행 시간: {} | URL: {}", article.getTitle(), article.getPublishedAt(), article.getUrl());
			});

			articleService.saveArticles(articles);
		} catch (Exception e) {
			log.error("Finviz 뉴스페이지 Fetch 실패", e);
		}
	}

	// TODO : 뉴스 와 블로그 정보까지 전부 긁어옴
	// TODO : 뉴스 정보만 긁어오게 변경해야할수도있음
	private List<Article> extractArticles(Document document) {
		Elements rows = document.select("tr.styled-row");
		List<Article> articles = new ArrayList<>();

		for (Element row : rows) {
			try {
				String title = row.selectFirst("td.news_link-cell a").text();
				String publishedAt = row.select("td.news_date-cell").text();
				String url = row.select("td.news_link-cell a").attr("abs:href");

				if (!title.isEmpty() && !publishedAt.isEmpty() && !url.isEmpty()) {

					// 오늘 날짜의 시간의 기사만 -> 지난 기사는 추출 X
					if (isTimeFormat(publishedAt)) {
						articles.add(Article.builder()
								.title(title)
								.url(url)
								// HH:mm -> yyyy-MM-dd HH:mm
								.publishedAt(timeConverter.usTimeToFormattedUSDate(publishedAt))
								.build());
					}
				}
			} catch (Exception e) {
				log.warn("기사 추출 실패", e);
			}
		}

		return articles;
	}

	//발행타입이 HH:mm 타입인지 아니라면 지난날의 기사
	private boolean isTimeFormat(String publishedAt) {
		try {
			SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
			timeFormat.setLenient(false);
			timeFormat.parse(publishedAt);
			return true;
		} catch (ParseException e) {
			log.warn("시간 형식 파싱 실패: {}", publishedAt);
			return false;
		}
	}
}
