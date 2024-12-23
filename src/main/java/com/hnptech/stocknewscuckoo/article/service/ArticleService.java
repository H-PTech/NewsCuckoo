package com.hnptech.stocknewscuckoo.article.service;

import com.hnptech.stocknewscuckoo.article.dto.response.ArticleResponse;
import com.hnptech.stocknewscuckoo.article.model.Article;
import com.hnptech.stocknewscuckoo.events.common.Event;
import com.hnptech.stocknewscuckoo.events.crawler.CrawlerFetchedEvent;
import java.util.List;

public interface ArticleService {

	List<ArticleResponse> getLatestArticles();

	void saveArticles(CrawlerFetchedEvent articles);
}
