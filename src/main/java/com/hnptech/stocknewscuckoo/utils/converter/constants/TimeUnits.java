package com.hnptech.stocknewscuckoo.utils.converter.constants;

public enum TimeUnits {
    MINUTES("min"),
    HOURS("hour"),
    DAYS("day");

    private final String unit;

    TimeUnits(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public static TimeUnits fromString(String unit) {
        for (TimeUnits timeUnit : TimeUnits.values()) {
            if (timeUnit.getUnit().equals(unit)) {
                return timeUnit;
            }
        }
        throw new IllegalArgumentException("Unknown time unit: " + unit);
    }
}