package com.vlad.putnikov.util;

import jakarta.persistence.AttributeConverter;

import java.time.Instant;
import java.time.YearMonth;
import java.sql.Date;
import java.time.ZoneId;

public class YearMonthDateAttributeConverter implements AttributeConverter<YearMonth, Date> {

    @Override
    public Date convertToDatabaseColumn(YearMonth attribute) {
        if (attribute != null) {
            return Date.valueOf(
                    attribute.atDay(1)
            );
        }
        return null;
    }

    @Override
    public YearMonth convertToEntityAttribute(Date dbData) {
        if (dbData != null) {
            return YearMonth.from(
                    Instant
                            .ofEpochMilli(dbData.getTime())
                            .atZone(ZoneId.of("Europe/Moscow"))
                            .toLocalDate()
            );
        }
        return null;
    }
}
