package com.hnptech.stocknewscuckoo.crawler.constants;

import lombok.Getter;

@Getter
public enum NewsSource {
	FINVIZ_MARKET("https://finviz.com/news.ashx", "finviz","market"),
	FINVIZ_STOCK("https://finviz.com/news.ashx?v=3", "finviz","stock");

	private final String url;
	private final String domain;
	private final String category;

	NewsSource(String url, String domain,String category) {
		this.url = url;
		this.domain = domain;
		this.category = category;
	}
}