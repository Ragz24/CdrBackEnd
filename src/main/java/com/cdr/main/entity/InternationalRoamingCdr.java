package com.cdr.main.entity;
import java.util.Random;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;



@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InternationalRoamingCdr 
{
	
	private String cdrType;
    private String startTimestamp;
    private String endTimestamp;
    private String callerIMSI;
    private String callerMSISDN;
    private String callerNetwork;
    private String callerLocation;
    private String callerCurrentNetwork;
    private String calleeNumber;
    private String calleeLocation;
    private String calleeNetwork;
    private String callType;
    private String callResult;
    private String callTerminationReason;
    private int callDuration;
    private String roamingPartner;
    private String roamingCharges;

}

