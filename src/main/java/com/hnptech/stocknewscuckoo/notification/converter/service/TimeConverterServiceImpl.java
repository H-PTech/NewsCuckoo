package com.hnptech.stocknewscuckoo.notification.converter.service;

import static com.hnptech.stocknewscuckoo.notification.converter.constants.TimeConversionConstants.US_TIME_ZONE;

import com.hnptech.stocknewscuckoo.notification.converter.constants.TimeConversionConstants;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.springframework.stereotype.Service;

@Service
public class TimeConverterServiceImpl implements TimeConverterService {

	@Override
	public String convertUSTimeToKoreaTime(String usTime) {
		LocalDateTime usLocalTime = LocalDateTime.parse(usTime, TimeConversionConstants.INPUT_FORMATTER);

		ZonedDateTime usZonedTime = usLocalTime.atZone(ZoneId.of(US_TIME_ZONE));

		ZonedDateTime koreaTime = usZonedTime.withZoneSameInstant(ZoneId.of(TimeConversionConstants.KOREA_TIME_ZONE));

		return koreaTime.format(TimeConversionConstants.OUTPUT_FORMATTER);
	}
}
