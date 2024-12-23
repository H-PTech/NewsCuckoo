package com.hnptech.stocknewscuckoo.events.crawler;

import com.hnptech.stocknewscuckoo.article.model.Article;
import com.hnptech.stocknewscuckoo.events.common.Event;
import java.util.List;

public record CrawlerFetchedEvent(List<Article> articles) implements Event {

}
