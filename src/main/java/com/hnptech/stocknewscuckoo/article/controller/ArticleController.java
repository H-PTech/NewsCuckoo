package com.hnptech.stocknewscuckoo.article.controller;

import com.hnptech.stocknewscuckoo.article.model.Article;
import com.hnptech.stocknewscuckoo.article.service.ArticleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

	private final ArticleService articleService;

	@GetMapping("/latest")
	public ResponseEntity<List<Article>> getLatestArticles() {
		return ResponseEntity.ok(articleService.getLatestArticles());
	}

}
