package com.hnptech.stocknewscuckoo.crawler.service;

import static com.hnptech.stocknewscuckoo.crawler.constants.NewsSource.FINVIZ;

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
public class NewsCrawlerServiceImpl implements NewsCrawlerService {


	private final ArticleProcessingServiceImpl articleProcessingService;

	@Override
	@Scheduled(fixedRate = 60000)
	public void crawlLatestNews() {
		log.info("Starting news crawling...");

		try {
			Document document = Jsoup.connect(FINVIZ.getUrl()).get();
			List<String> articleLinks = extractArticleLinks(document);

			for (String articleUrl : articleLinks) {
				articleProcessingService.processArticle(articleUrl);
			}
		} catch (Exception e) {
			log.error("Finviz 뉴스페이지 Fetch 실패", e);
		}
	}

	private List<String> extractArticleLinks(Document document) {
		Elements newsLinks = document.select("a[href]");
		List<String> articleLinks = new ArrayList<>();

		for (Element link : newsLinks) {
			String href = link.attr("abs:href");
			if (href.contains("https://") && !href.contains("finviz")) {
				articleLinks.add(href);
			}
		}
		return articleLinks;
	}
}
