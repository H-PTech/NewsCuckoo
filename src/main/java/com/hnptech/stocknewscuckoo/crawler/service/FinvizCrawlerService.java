package com.hnptech.stocknewscuckoo.crawler.service;

import static com.hnptech.stocknewscuckoo.crawler.constants.NewsSource.FINVIZ;

import com.hnptech.stocknewscuckoo.crawler.model.Article;
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


	@Override
	@Scheduled(fixedRate = 60000)
	public void crawlLatestNews() {
		log.info("크롤링 시작");

		try {
			Document document = Jsoup.connect(FINVIZ.getUrl()).get();
			List<Article> articles = extractArticles(document);
			articles.forEach(article -> log.info("제목: {} | 발행 시간: {} | URL: {}", article.getTitle(), article.getPublishedAt(), article.getUrl()));
		} catch (Exception e) {
			log.error("Finviz 뉴스페이지 Fetch 실패", e);
		}
	}

	private List<Article> extractArticles(Document document) {
		Elements rows = document.select("tr.styled-row");
		List<Article> articles = new ArrayList<>();

		for (Element row : rows) {
			try {
				String title = row.select("td.news_link-cell a").text();
				String publishedAt = row.select("td.news_date-cell").text();
				String url = row.select("td.news_link-cell a").attr("abs:href");

				if (!title.isEmpty() && !publishedAt.isEmpty() && !url.isEmpty()) {
					articles.add(Article.builder().
							title(title)
							.url(url)
							.publishedAt(publishedAt)
							.build());
				}
			} catch (Exception e) {
				log.warn("기사 추출 실패", e);
			}
		}

		return articles;
	}
}