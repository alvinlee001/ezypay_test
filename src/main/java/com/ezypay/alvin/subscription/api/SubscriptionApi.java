package com.ezypay.alvin.subscription.api;

import com.ezypay.alvin.subscription.dto.request.SubscriptionApiRequest;
import com.ezypay.alvin.subscription.dto.response.SubscriptionApiResponse;
import com.ezypay.alvin.subscription.service.DateRangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SubscriptionApi {

    private final DateRangeService dateRangeService;

    @PostMapping("subscription/create")
    public ResponseEntity<SubscriptionApiResponse> createSubscriptionApi(@Valid @RequestBody SubscriptionApiRequest subscriptionRequest) {
        SubscriptionApiResponse response = new SubscriptionApiResponse();
        response.setSubscriptionType(subscriptionRequest.getSubscriptionType());
        response.setAmount(subscriptionRequest.getAmount());
        switch (subscriptionRequest.getSubscriptionType()) {
            case DAILY:
                response.setInvoiceDates(dateRangeService.findDailyDatesInDateRange(subscriptionRequest.getStartDate(), subscriptionRequest.getEndDate()));
                break;
            case WEEKLY:
                response.setInvoiceDates(dateRangeService.findWeeklyDatesInDateRange(subscriptionRequest.getDayOfWeek(), subscriptionRequest.getStartDate(), subscriptionRequest.getEndDate()));
                break;
            case MONTHLY:
                response.setInvoiceDates(dateRangeService.findMonthlyDatesInDateRange(subscriptionRequest.getDayOfMonth(), subscriptionRequest.getStartDate(), subscriptionRequest.getEndDate()));
                break;
            default:
                // not possible to reach, as spring thrown 400 bad request before it happens,
        }
        return ResponseEntity.ok(response);
    }



}
