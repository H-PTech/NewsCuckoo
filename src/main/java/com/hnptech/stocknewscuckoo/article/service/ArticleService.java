package com.hnptech.stocknewscuckoo.article.service;

import com.hnptech.stocknewscuckoo.article.model.Article;
import java.util.List;

public interface ArticleService {

	List<Article> getLatestArticles();

	void saveArticles(List<Article> articles);
}
