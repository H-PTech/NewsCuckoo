package com.hnptech.stocknewscuckoo.events.article;

import com.hnptech.stocknewscuckoo.article.model.Article;

import com.hnptech.stocknewscuckoo.events.common.Event;
import java.util.List;

public record ArticleCreatedEvent(List<Article> articles) implements Event {

}