package com.cdr.main.functionality;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.cdr.main.entity.DataCdr;

public class DataCdrGeneration {
    
    public DataCdr generateDataCDR() {
        Random rand = new Random();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String sessionStatus = (rand.nextBoolean()) ? "Completed" : "Failed";

        String[] sessionTypes = { "HTTP", "FTP", "Email", "Video Streaming", "VoIP" };
        String sessionType = sessionTypes[rand.nextInt(sessionTypes.length)];

        int sessionDuration = (sessionStatus.equals("Completed")) ? generateSessionDuration(sessionType, rand) : 0;

        int dataUsage = (sessionDuration == 0) ? 0 : rand.nextInt(1500) + 1;

        Date sessionStartDate = generateRandomDate(2022, new Date(), rand);
        Date sessionStartTime = new Date(sessionStartDate.getTime() - (sessionDuration * 1000));
        Date sessionEndTime = sessionStartDate;

        if (sessionEndTime.before(sessionStartTime)) {
            sessionEndTime = new Date(sessionStartTime.getTime() + (sessionDuration * 1000));
        }

        String sessionDirection = (rand.nextBoolean()) ? "Outgoing" : "Incoming";

        String callerIPAddress = generateRandomIPAddress(rand);
        String calleeIPAddress = (sessionStatus.equals("Failed")) ? "N/A" : generateRandomIPAddress(rand);

        String sessionStartTimeStr = dateFormat.format(sessionStartTime);
        String sessionEndTimeStr = dateFormat.format(sessionEndTime);

        DataCdr dataCdr = new DataCdr();

        dataCdr.setSessionStartTime(sessionStartTimeStr);
        dataCdr.setSessionEndTime(sessionEndTimeStr);
        dataCdr.setSessionDirection(sessionDirection);
        dataCdr.setSessionType(sessionType);
        dataCdr.setSessionDuration(sessionDuration / 60);
        dataCdr.setSessionStatus(sessionStatus);
        dataCdr.setDataUsage(dataUsage);
        dataCdr.setCallerIPAddress(callerIPAddress);
        dataCdr.setCalleeIPAddress(calleeIPAddress);

        return dataCdr;
    }

    public static String generateRandomIPAddress(Random rand) {
        int part1 = rand.nextInt(256);
        int part2 = rand.nextInt(256);
        int part3 = rand.nextInt(256);
        int part4 = rand.nextInt(256);
        return part1 + "." + part2 + "." + part3 + "." + part4;
    }
    
    public static int generateSessionDuration(String sessionType, Random rand) {
        if (sessionType.equals("Video Streaming") || sessionType.equals("FTP") || sessionType.equals("HTTP")) {
            return rand.nextInt(5 * 60*60) + 60;
        } else if (sessionType.equals("VoIP")) {
            return rand.nextInt(2 * 60 * 60) + 60;
        } else if (sessionType.equals("Email")) {
            return rand.nextInt((24 * 7) * 60 *60) + 60; 
        } else {
            return 0;
        }
    }
    
    public static Date generateRandomDate(int startYear, Date endDate, Random rand) {
        long startMillis = new Date(startYear - 1900, 0, 1).getTime();
        long endMillis = endDate.getTime();
        long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
        return new Date(randomMillisSinceEpoch);
    }
}
