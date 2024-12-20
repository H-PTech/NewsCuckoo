package com.hnptech.stocknewscuckoo.translation.service;

import static com.hnptech.stocknewscuckoo.translation.constants.OpenAiConstants.OPEN_AI_SYSTEM_PROMPT;
import static com.hnptech.stocknewscuckoo.translation.constants.OpenAiConstants.OPEN_AI_USER_PROMPT;

import java.util.List;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Component;

@Component
public class OpenAiService implements TranslationService{

	private final OpenAiChatModel chatModel;

	public OpenAiService(OpenAiChatModel chatModel) {
		this.chatModel = chatModel;
	}


	@Override
	public String translate(String title) {
		Message userMessage = new UserMessage(OPEN_AI_USER_PROMPT.getPrompt() + title);
		Message systemMessage = new SystemPromptTemplate(OPEN_AI_SYSTEM_PROMPT.getPrompt()).createMessage();

		Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
		return chatModel.call(prompt).getResult().getOutput().getContent();
	}
}
