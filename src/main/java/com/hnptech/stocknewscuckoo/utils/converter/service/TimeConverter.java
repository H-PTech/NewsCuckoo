package com.hnptech.stocknewscuckoo.utils.converter.service;

import java.time.LocalDateTime;

public interface TimeConverter {
	LocalDateTime convertUSTimeToKoreaTime(LocalDateTime usTime);

	LocalDateTime usTimeToFormattedUSDate(String usTime);
}
