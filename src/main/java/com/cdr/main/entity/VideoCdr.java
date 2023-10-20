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
public class VideoCdr 
{
	private String callStartTimestamp;
    private String callEndTimestamp;
    private String callerIdentifier;
    private String calleeIdentifier;
    private int callDuration;
    private String networkType;
    private String callQualityMetrics;
    private String callType;
    private String callStatus;
    private String deviceInformation;
    private String networkUsage;
    private String codecInformation;
    private String callIdentifier;

}
