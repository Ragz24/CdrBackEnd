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
public class VoIPCdr 
{
	private String callID;
    private String callerIP;
    private String calleeIP;
    private String startTimestamp;
    private String endTimestamp;
    private int callDuration;
    private double packetLoss;
    private int jitter;
    private int delay;
    private String callType;
    private String callDirection;
    private String codec;
    private String callRecordingStatus;
    private String networkType;
    private int callSetUpTime;
    private String callTerminationReason;
    private String callResult;

}
