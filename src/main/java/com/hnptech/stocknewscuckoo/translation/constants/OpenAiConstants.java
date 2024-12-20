package com.hnptech.stocknewscuckoo.translation.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OpenAiConstants {

	OPEN_AI_SYSTEM_PROMPT("""
        You are a professional translator specializing in finance and stock market content.
        Please translate the provided stock market headlines into fluent Korean while maintaining the original meaning and financial context.
    """),

	OPEN_AI_USER_PROMPT("""
        Translate the following stock market news headline into Korean:
    """);

	private final String prompt;
}