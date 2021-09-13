package com.ezypay.alvin.subscription.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DateRangeServiceImpl implements DateRangeService{

    //TODO: save created date range is too boring a task, and since the api is currently stateless, where records are
    // related to any user, will skip for now, as i have other more interesting things to do.
    // Due to time constraint, code below does not use DateLocal as I am at the moment not familiar with the api. Hence,
    // a dumber appraoch is used for this test. Of course, more serious ways will be considered, such as making sure the
    // correct timezone is applied, which is always a pain, but necessary for accurate representation of time.
    // I am not an expert in timezones, but i hope i have other things to offer.


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
