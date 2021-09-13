package com.ezypay.alvin.subscription.dto.response;

import com.ezypay.alvin.subscription.constant.Subscription;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SubscriptionApiResponse {
    private BigDecimal amount;
    private Subscription subscriptionType;

//    @JsonSerialize(contentUsing = JsonDateSerializer.class)
    private List<Date> invoiceDates;
}
