package com.hnptech.stocknewscuckoo.notification.notifier.implement;

import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.ApsAlert;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.hnptech.stocknewscuckoo.notification.constants.FcmConstants;
import com.hnptech.stocknewscuckoo.notification.dto.response.NotificationItem;
import com.hnptech.stocknewscuckoo.notification.notifier.Notifier;
import com.hnptech.stocknewscuckoo.token.dto.DeviceTokenItem;
import com.hnptech.stocknewscuckoo.token.service.DeviceTokenService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FcmNotifier implements Notifier {

	private final DeviceTokenService deviceTokenService;

	@Override
	public void sendNotification(NotificationItem request) {
		List<DeviceTokenItem> tokens = deviceTokenService.getAllTokens();

		for (DeviceTokenItem tokenItem : tokens) {
			try {
				ApnsConfig apnsConfig = ApnsConfig.builder()
						.setAps(Aps.builder()
								.setAlert(ApsAlert.builder()
										.setTitle(request.title())
										.setBody(request.url())
										.build())
								.setSound(FcmConstants.DEFAULT_SOUND.getValue())
								.build())
						.build();

				Message message = Message.builder()
						.setToken(tokenItem.deviceToken())
						.setNotification(Notification.builder()
								.setTitle(request.title())
								.setBody(request.url())
								.build())
						.setApnsConfig(apnsConfig)
						.build();

				String response = FirebaseMessaging.getInstance().send(message);
				log.info("토큰 보내기 성공 {}: {}", tokenItem.deviceToken(), response);

			} catch (Exception e) {
				log.error("토큰 보내기 실패 {}: {}", tokenItem.deviceToken(), e.getMessage());
			}
		}
	}
}
