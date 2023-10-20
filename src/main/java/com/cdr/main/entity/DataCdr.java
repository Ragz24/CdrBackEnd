package com.cdr.main.entity;

import org.springframework.stereotype.Component;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataCdr 
{
	
	private String sessionStartTime;
    private String sessionEndTime;
    private String sessionDirection;
    private String sessionType;
    private int sessionDuration;
    private String sessionStatus;
    private int dataUsage;
    private String callerIPAddress;
    private String calleeIPAddress;

}
