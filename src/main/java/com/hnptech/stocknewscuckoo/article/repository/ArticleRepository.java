package com.hnptech.stocknewscuckoo.article.repository;

import com.hnptech.stocknewscuckoo.article.model.Article;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

		List<Article> findTop20ByOrderByPublishedAtDesc();
}
