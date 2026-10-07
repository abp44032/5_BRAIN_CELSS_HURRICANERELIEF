package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    private LocalDateTime time;

    public DateTime() {
        this.time = LocalDateTime.now();
    }

    public DateTime(LocalDateTime time) {
        this.time = time;
    }

    public static DateTime timestamp() {
        return new DateTime(LocalDateTime.now());
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.time.format(formatter);
    }

    public static void main(String[] args) {
        DateTime now = DateTime.timestamp();
        System.out.println("Timestamp: " + now);
    }
}
