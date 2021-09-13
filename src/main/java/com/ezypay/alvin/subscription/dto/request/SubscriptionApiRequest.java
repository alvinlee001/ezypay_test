package com.ezypay.alvin.subscription.dto.request;

import com.ezypay.alvin.subscription.constant.Subscription;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.Date;

@Data
public class SubscriptionApiRequest {
    private BigDecimal amount;
    private Subscription subscriptionType;
    private DayOfWeek dayOfWeek;

    @Min(value = 1, message = "dayOfMonth should not be less than 1")
    @Max(value = 31, message = "dayOfMonth should not be greater than 31")
    private int dayOfMonth;
    private Date startDate;
    private Date endDate;
}
