package com.ezypay.alvin.subscription.service;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

public interface DateRangeService {
    List<Date> findWeeklyDatesInDateRange(DayOfWeek dayOfWeek, Date startDate, Date endDate);
    List<Date> findMonthlyDatesInDateRange(int dayOfMonth, Date startDate, Date endDate);
    List<Date> findDailyDatesInDateRange(Date startDate, Date endDate);
}
