package com.cdr.main.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationCdr 
{
	private String updateTimestamp;
    private String cellID;
    private double latitude;
    private double longitude;
    private int locationAreaCode;
    private String roamingStatus;
    private int signalStrength;
    private double locationAccuracy;
    private String locationSource;
    private String locationMethod;
    private String imei;
}
