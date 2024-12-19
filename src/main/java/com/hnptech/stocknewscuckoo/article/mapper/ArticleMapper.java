package com.hnptech.stocknewscuckoo.article.mapper;

import com.hnptech.stocknewscuckoo.article.dto.response.ArticleResponse;
import com.hnptech.stocknewscuckoo.article.model.Article;
import java.util.List;

public interface ArticleMapper {
	ArticleResponse toResponse(Article article);
	List<ArticleResponse> toResponseList(List<Article> articles);

}
