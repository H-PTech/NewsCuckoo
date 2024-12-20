package com.hnptech.stocknewscuckoo.notification.notifier;

import static com.hnptech.stocknewscuckoo.notification.constants.WhatsAppConstants.PHONE_NUMBER_ID;
import static com.hnptech.stocknewscuckoo.notification.constants.WhatsAppConstants.RECIPIENT_PHONE_NUMBER;
import static com.hnptech.stocknewscuckoo.notification.constants.WhatsAppConstants.URL;
import static com.hnptech.stocknewscuckoo.notification.constants.WhatsAppConstants.VERSION;

import com.hnptech.stocknewscuckoo.notification.constants.WhatsAppPayloadField;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WhatsAppNotifier implements Notifier {

	@Override
	public void sendNotification(String message) {

		String urlString = String.format(URL.getValue(), VERSION.getValue(),
				PHONE_NUMBER_ID.getValue());

		try {
			String payload = WhatsAppPayloadField.buildPayload(RECIPIENT_PHONE_NUMBER.getValue(),
					message);

			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI(urlString))
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(payload))
					.build();

			HttpResponse<String> response = client.send(request,
					HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == 200) {
				log.info("WhatsApp Message sent successfully.");
			} else {
				System.out.println(
						"WhatsApp Failed to send message. Response Code: " + response.statusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
