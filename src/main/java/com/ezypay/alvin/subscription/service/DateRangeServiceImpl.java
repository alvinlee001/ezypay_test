package com.ezypay.alvin.subscription.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DateRangeServiceImpl implements DateRangeService{

    @Override
    public List<Date> findWeeklyDatesInDateRange(DayOfWeek dayOfWeek, Date startDate, Date endDate) {

        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        List<Date> results = new ArrayList<>();
        Calendar resultPlaceholderCal = Calendar.getInstance();
        resultPlaceholderCal.setTime(startDate);
        resultPlaceholderCal.set(Calendar.DAY_OF_WEEK, dayOfWeek.getValue() - 1);

        while(resultPlaceholderCal.before(end) || resultPlaceholderCal.equals(end)) {
            if ( resultPlaceholderCal.after(start) || resultPlaceholderCal.equals(start)) {
                results.add(resultPlaceholderCal.getTime());
            }
            resultPlaceholderCal.add(Calendar.DATE, 7);
        }
        return results;
    }

    @Override
    public List<Date> findMonthlyDatesInDateRange(int dayOfMonth, Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        List<Date> results = new ArrayList<>();
        Calendar resultPlaceholderCal = Calendar.getInstance();
        resultPlaceholderCal.setTime(startDate);
        resultPlaceholderCal.set(start.get(Calendar.YEAR), start.get(Calendar.MONTH), dayOfMonth);


        while(resultPlaceholderCal.before(end) || resultPlaceholderCal.equals(end)) {
            if ( resultPlaceholderCal.after(start) || resultPlaceholderCal.equals(start)) {
                results.add(resultPlaceholderCal.getTime());
            }
            resultPlaceholderCal.add(Calendar.MONTH, 1);
        }
        return results;
    }

    @Override
    public List<Date> findDailyDatesInDateRange(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        List<Date> results = new ArrayList<>();
        Calendar resultPlaceholderCal = Calendar.getInstance();
        resultPlaceholderCal.setTime(startDate);


        while(resultPlaceholderCal.before(end) || resultPlaceholderCal.equals(end)) {
            if ( resultPlaceholderCal.after(start) || resultPlaceholderCal.equals(start)) {
                results.add(resultPlaceholderCal.getTime());
            }
            resultPlaceholderCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return results;
    }
}
