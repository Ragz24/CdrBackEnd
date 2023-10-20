package com.cdr.main.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.cdr.main.functionality.EventCdrGeneration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventCdr 
{
	private String eventTimestamp;
    private String imsi;
    private String msisdn;
    private String eventIdentifier;
    private String eventSource;
    private String eventOutcome;
    private String eventDuration;
    private String userInfo;

}
