package com.cdr.main.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingCdr 
{
	private String subscriberID;
    private String timestamp;
    private String serviceType;
    private String originatingPhoneNumber;
    private String terminatingPhoneNumber;
    private int callDuration;
    private String rate;
    private String totalCost;
    private String taxAndSurcharges;
    private String billingPeriod;
    private String paymentMethod;
    private String qualityOfServiceMetrics;
    private String paymentStatus;
}
