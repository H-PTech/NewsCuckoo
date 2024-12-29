package com.hnptech.stocknewscuckoo.article.controller;

import com.hnptech.stocknewscuckoo.article.dto.response.ArticleResponse;
import com.hnptech.stocknewscuckoo.article.service.ArticleService;
import com.hnptech.stocknewscuckoo.common.result.ApiResult;
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
	public ApiResult<List<ArticleResponse>> getLatestArticles() {
		return ApiResult.success(articleService.getLatestArticles());
	}

}
