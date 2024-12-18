package com.hnptech.stocknewscuckoo.crawler.service;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ArticleProcessingServiceImpl implements ArticleProcessingService {

    public void processArticle(String articleUrl) {
        try {
            log.info("Fetching article: {}", articleUrl);
            Document articleDocument = Jsoup.connect(articleUrl).get();
            String title = articleDocument.title();
            String content = extractArticleContent(articleDocument);

            log.info("제목: {}", title);
            log.info("내용: {}", content);
            log.info("----------------------------------------------------");
        } catch (Exception e) {
            log.error("기사 추출 실패: {}", articleUrl, e);
        }
    }

    private String extractArticleContent(Document articleDocument) {
        Elements paragraphs = articleDocument.select("p");
        StringBuilder content = new StringBuilder();

        for (Element paragraph : paragraphs) {
            content.append(paragraph.text()).append("\n");
        }
        return content.toString();
    }
}
