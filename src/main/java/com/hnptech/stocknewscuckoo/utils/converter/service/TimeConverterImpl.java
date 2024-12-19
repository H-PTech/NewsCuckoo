package com.hnptech.stocknewscuckoo.utils.converter.service;

import static java.util.Locale.ENGLISH;

import com.hnptech.stocknewscuckoo.utils.converter.constants.TimeUnits;
import com.hnptech.stocknewscuckoo.utils.converter.constants.TimeZones;
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
		ZonedDateTime usZonedTime = usTime.atZone(ZoneId.of(TimeZones.US.getZoneId()));
		ZonedDateTime koreaTime = usZonedTime.withZoneSameInstant(ZoneId.of(TimeZones.KOREA.getZoneId()));

		return koreaTime.toLocalDateTime();
	}
	@Override
	public LocalDateTime usTimeToFormattedUSDate(String usTime) {
		try {
			// 현재 날짜를 가져오기 (미국 시간대 기준)
			ZoneId zoneId = ZoneId.of(TimeZones.US.getZoneId());
			LocalDate currentDate = LocalDate.now(zoneId);

			// 시간 파싱
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma",ENGLISH);
			usTime = usTime.toUpperCase(); // AM/PM 확실히 처리
			LocalTime time = LocalTime.parse(usTime, timeFormatter);

			return LocalDateTime.of(currentDate, time);
		} catch (Exception e) {
			throw new IllegalArgumentException("날짜 포맷 형식 틀림: " + usTime, e);
		}
	}

	@Override
	public LocalDateTime convertUsRelativeTimeToAbsoluteTime(String usTime) {
		int timeValue = Integer.parseInt(usTime.split(" ")[0]);
		String timeUnit = usTime.split(" ")[1];

		LocalDateTime now = LocalDateTime.now(ZoneId.of(TimeZones.US.getZoneId()));

		TimeUnits parsedTimeUnit = TimeUnits.fromString(timeUnit);

		return switch (parsedTimeUnit) {
			case MINUTES -> now.minusMinutes(timeValue);
			case HOURS -> now.minusHours(timeValue);
			case DAYS -> now.minusDays(timeValue);
		};
	}

}
