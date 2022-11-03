package com.example.movie.trend.dto;

import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Data
public class TimeWindow {

    private LocalDate start;
    private LocalDate end;
    private boolean now;

//    DB 조회시 사용될 객체로 현재 날짜 조회인지 아닌지 여부에 따라 해당주 월요일, 일요일 초기화하여서 사용하는 객체
    public TimeWindow(String timeWindow) {
        LocalDate nowDate = LocalDate.now();
        if (timeWindow.equals("day")) {
            this.now = true;
        } else {
            this.now = false;
            start = nowDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            end = nowDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        }

    }
}
