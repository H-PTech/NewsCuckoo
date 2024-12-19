package com.hnptech.stocknewscuckoo.utils.converter.constants;

import lombok.Getter;

@Getter
public enum TimeZones {
    KOREA("Asia/Seoul"),
    US("America/New_York");

    private final String zoneId;

    TimeZones(String zoneId) {
        this.zoneId = zoneId;
    }
}
