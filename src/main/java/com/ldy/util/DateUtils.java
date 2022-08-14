package com.ldy.util;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
@Slf4j
public class DateUtils {
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime formatLocalDateTime(LocalDateTime localDateTime) {
        String datePattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(datePattern);
        String format = dtf.format(localDateTime);
        log.info(format);
        return LocalDateTime.parse(format,dtf);
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime localDateTime = formatLocalDateTime(now);
        log.info(String.valueOf(localDateTime));
    }
}
