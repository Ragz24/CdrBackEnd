package com.cdr.main.functionality;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.cdr.main.entity.BillingCdr;
import com.cdr.main.entity.EventCdr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class EventCdrGeneration 
{    
    public static EventCdr generateEventCallBillingCDR() 
    {           
        Random rand = new Random();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date eventTimestamp = generateRandomDate(2022, new Date(), rand);

        String imsi = generateRandomIMSI();
        String msisdn = generateRandomMSISDN(rand);
        String eventIdentifier =  generateEventIdentifier(generateRandomEventType(rand), eventTimestamp, rand);
        String eventSource = generateRandomEventSource();
        String eventOutcome = generateRandomEventOutcome();
        String eventDuration = "N/A (instantaneous event)";
        String userInfo = "Customer ID: " + generateRandomCustomerID();

 

        System.out.println("EventTimestamp: " + dateFormat.format(eventTimestamp));
        System.out.println("IMSI: " + imsi);
        System.out.println("MSISDN: " + msisdn);
        System.out.println("Event Identifier: " + eventIdentifier);
        System.out.println("Event Source: " + eventSource);
        System.out.println("Event Outcome/Status: " + eventOutcome);
        System.out.println("Event Duration: " + eventDuration);
        System.out.println("User Information: " + userInfo); 
        
        
        EventCdr event = new EventCdr();

        event.setEventTimestamp(dateFormat.format(eventTimestamp));
        event.setImsi(imsi);
        event.setMsisdn(msisdn);
        event.setEventIdentifier(eventIdentifier);
        event.setEventSource(eventSource);
        event.setEventOutcome(eventOutcome);
        event.setEventDuration(eventDuration);
        event.setUserInfo(userInfo); 
        
        return event;
    }

 

    public static Date generateRandomDate(int startYear, Date endDate, Random rand) {
    long startMillis = new Date(startYear - 1900, 0, 1).getTime();
    long endMillis = endDate.getTime();
    long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
    return new Date(randomMillisSinceEpoch);
    }

 

    public static String generateRandomIMSI() {
        Random rand = new Random();
        StringBuilder imsi = new StringBuilder("404260");
        for (int i = 1; i < 10; i++) {
            imsi.append(rand.nextInt(10));
        }
        return imsi.toString();
    }

 

    public static String generateRandomMSISDN(Random rand) {
        int[] validFirstDigits = {6, 7, 8, 9};
        int firstDigit = validFirstDigits[rand.nextInt(validFirstDigits.length)];
        StringBuilder msisdn = new StringBuilder("+91");
        msisdn.append(firstDigit);
        for (int i = 0; i < 9; i++) {
            msisdn.append(rand.nextInt(10));
        }
        return msisdn.toString();
    }
   public static String generateRandomEventType(Random rand) {
        String[] eventTypes = {"Activation", "Deactivation", "Balance Inquiry", "Plan Change","Upgrade",
    "Service Change","Payment","Customer Complaint"};
        return eventTypes[rand.nextInt(eventTypes.length)];
    }

 
    public static String generateEventIdentifier(String eventType, Date eventTimestamp, Random rand) {
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String year = yearFormat.format(eventTimestamp);
        int uniqueNumber = rand.nextInt(100000); 
        return eventType + "-" + year + "-" + uniqueNumber;
    }

 

    public static String generateRandomEventSource() {
        String[] eventSources = {"Retail Store System", "Online Portal", "Mobile App","Customer Support Center","Email Communication"};
        Random rand = new Random();
        return eventSources[rand.nextInt(eventSources.length)];
    }

 

    public static String generateRandomEventOutcome() {
        String[] eventOutcomes = {"Successful", "Failed", "Incomplete"};
        Random rand = new Random();
        return eventOutcomes[rand.nextInt(eventOutcomes.length)];
    }

 

    public static int generateRandomCustomerID() {
        Random rand = new Random();
        return 10000 + rand.nextInt(90000);
    }

}
