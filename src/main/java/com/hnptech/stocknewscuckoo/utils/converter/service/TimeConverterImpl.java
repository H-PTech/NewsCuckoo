package com.hnptech.stocknewscuckoo.utils.converter.service;

import static com.hnptech.stocknewscuckoo.utils.converter.constants.TimeConversionConstants.US_TIME_ZONE;
import static java.util.Locale.ENGLISH;

import com.hnptech.stocknewscuckoo.utils.converter.constants.TimeConversionConstants;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class TimeConverterImpl implements TimeConverter {

	@Override
	public LocalDateTime convertUSTimeToKoreaTime(LocalDateTime usTime) {
		ZonedDateTime usZonedTime = usTime.atZone(ZoneId.of(US_TIME_ZONE));
		ZonedDateTime koreaTime = usZonedTime.withZoneSameInstant(ZoneId.of(TimeConversionConstants.KOREA_TIME_ZONE));

		return koreaTime.toLocalDateTime();
	}
	@Override
	public LocalDateTime usTimeToFormattedUSDate(String usTime) {
		try {
			// 현재 날짜를 가져오기 (미국 시간대 기준)
			ZoneId zoneId = ZoneId.of(US_TIME_ZONE);
			LocalDate currentDate = LocalDate.now(zoneId);

			// 시간 파싱
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma",ENGLISH);
			usTime = usTime.toUpperCase(); // AM/PM 확실히 처리
			LocalTime time = LocalTime.parse(usTime, timeFormatter);

			// 날짜와 시간 합치기
			LocalDateTime dateTime = LocalDateTime.of(currentDate, time);

			return dateTime;
		} catch (Exception e) {
			throw new IllegalArgumentException("날짜 포맷 형식 틀림: " + usTime, e);
		}
	}

}
