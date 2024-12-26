package com.hnptech.stocknewscuckoo.article.constants;

import lombok.Getter;

@Getter
public enum ArticleCategory {

	 MARKET("market"),
	STOCK("stock");

	 private final String value;

	ArticleCategory(String value) {
		this.value = value;
	}
}
