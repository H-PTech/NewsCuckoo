package com.hnptech.stocknewscuckoo.crawler.constants;

import lombok.Getter;

@Getter
public enum NewsSource {
	FINVIZ("https://finviz.com/news.ashx", "finviz");

	private final String url;
	private final String domain;

	NewsSource(String url, String domain) {
		this.url = url;
		this.domain = domain;
	}
}