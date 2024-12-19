package com.hnptech.stocknewscuckoo.utils.converter.constants;


import java.time.format.DateTimeFormatter;

public class TimeConversionConstants {
	public static final String KOREA_TIME_ZONE = "Asia/Seoul";

	public static final String US_TIME_ZONE = "America/New_York";
	public static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("hh:mma");
	public static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
}
