package com.hnptech.stocknewscuckoo.notification.notifier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.BotSession;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.AfterBotRegistration;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Slf4j
@Component
public class TelegramNotifier implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer,Notifier {

	private String token;
	private String chatId;

	private final TelegramClient telegramClient;

	@Autowired
	public TelegramNotifier(@Value("${telegram.bot.token}") String token,
			@Value("${telegram.bot.chat-id}") String chatId) {
		this.token = token;
		this.chatId = chatId;
		this.telegramClient = new OkHttpTelegramClient(getBotToken());
	}

	@Override
	public String getBotToken() {
		return token;
	}

	@Override
	public LongPollingUpdateConsumer getUpdatesConsumer() {
		return this;
	}

	@Override
	public void consume(Update update) {
		// 메시지 처리
		if (update.hasMessage() && update.getMessage().hasText()) {
			// 메시지 처리 로직
		}
	}

	@Override
	public void sendNotification(String message) {
		SendMessage sendMessage = SendMessage.builder()
				.chatId(chatId)
				.text(message)
				.build();
		try {
			telegramClient.execute(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@AfterBotRegistration
	public void afterRegistration(BotSession botSession) {
		System.out.println("Registered bot running state is: " + botSession.isRunning());
	}
}
