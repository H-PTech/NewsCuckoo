package com.hnptech.stocknewscuckoo.utils.converter.constants;

public enum TimeZones {
    KOREA("Asia/Seoul"),
    US("America/New_York");

    private final String zoneId;

    TimeZones(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneId() {
        return zoneId;
    }
}
