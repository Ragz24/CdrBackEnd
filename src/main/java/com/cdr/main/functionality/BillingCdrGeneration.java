package com.cdr.main.functionality;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.cdr.main.entity.BillingCdr;

public class BillingCdrGeneration {

    public static BillingCdr generateBillingCDR() {

        Random rand = new Random();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date callTimestamp = generateRandomDate(2022, new Date(), rand);

        String subscriberID = generateRandomSubscriberID(rand);
        String serviceType = "Voice Call";
        String originatingPhoneNumber = generateIndianPhoneNumber(rand);
        String terminatingPhoneNumber = generateIndianPhoneNumber(rand);
        int callDuration = generateRandomCallDuration(rand);
        double rate = generateRandomCallRate(rand);
        double totalCost = calculateTotalCost(callDuration, rate);
        double taxAndSurcharges = calculateTaxesAndSurcharges(totalCost);
        String billingPeriod = generateRandomBillingPeriod(rand); 
        String paymentMethod = generateRandomPaymentMethod(rand);
        String paymentStatus = generateRandomPaymentStatus(rand, billingPeriod);
        String qualityOfServiceMetrics = generateRandomQoSMetrics(rand);

        System.out.println("Generated Voice Call Billing CDR:");
        System.out.println("Subscriber ID: " + subscriberID);
        System.out.println("Timestamp: " + dateFormat.format(callTimestamp));
        System.out.println("Service Type: " + serviceType);
        System.out.println("Originating Phone Number: " + originatingPhoneNumber);
        System.out.println("Terminating Phone Number: " + terminatingPhoneNumber);
        System.out.println("Call Duration: " + callDuration + " seconds");
        System.out.println("Rate: $" + rate + " per minute");
        System.out.println("Total Cost: $" + totalCost);
        System.out.println("Tax and Surcharges: $" + taxAndSurcharges);
        System.out.println("Billing Period: " + billingPeriod);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Quality of Service Metrics: " + qualityOfServiceMetrics);
        System.out.println("Payment Status: " + paymentStatus);

        String formattedRate = String.format("%.2f", rate);
        String formattedTotalCost = String.format("%.2f", totalCost);
        String formattedTaxAndSurcharges = String.format("%.2f", taxAndSurcharges);

        BillingCdr billing = new BillingCdr();
        billing.setSubscriberID(subscriberID);
        billing.setTimestamp(dateFormat.format(callTimestamp));
        billing.setServiceType(serviceType);
        billing.setOriginatingPhoneNumber(originatingPhoneNumber);
        billing.setTerminatingPhoneNumber(terminatingPhoneNumber);
        billing.setCallDuration(callDuration / 60); 
        billing.setRate(formattedRate); 
        billing.setTotalCost(formattedTotalCost); 
        billing.setTaxAndSurcharges(formattedTaxAndSurcharges); 
        billing.setBillingPeriod(billingPeriod);
        billing.setPaymentMethod(paymentMethod);
        billing.setQualityOfServiceMetrics(qualityOfServiceMetrics);
        billing.setPaymentStatus(paymentStatus);

        return billing;
    }

    public static String generateRandomSubscriberID(Random rand) {
        return "SUB" + rand.nextInt(10000);
    }

    public static String generateIndianPhoneNumber(Random rand) {
        String[] prefixes = {"6", "7", "8", "9"};
        String phoneNumber = "+91" + prefixes[rand.nextInt(prefixes.length)];
        for (int i = 0; i < 8; i++) {
            phoneNumber += rand.nextInt(10);
        }
        return phoneNumber;
    }

    public static int generateRandomCallDuration(Random rand) {
        return rand.nextInt(7200 - 5 + 1) + 5;
    }

    public static double generateRandomCallRate(Random rand) {
        return rand.nextDouble() * 0.2 + 0.1; 
    }

    public static String generateRandomPaymentMethod(Random rand) {
        String[] paymentMethods = {"Credit Card","PayPal", "Mobile Wallet","Debit Card","Net Banking"};
        return paymentMethods[rand.nextInt(paymentMethods.length)];
    }

    public static String generateRandomQoSMetrics(Random rand) {
        return (rand.nextInt(31) + 70) + "%"; 
    }

    public static Date generateRandomDate(int startYear, Date endDate, Random rand) {
        long startMillis = new Date(startYear - 1900, 0, 1).getTime();
        long endMillis = endDate.getTime();
        long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
        return new Date(randomMillisSinceEpoch);
    }

    public static double calculateTotalCost(int callDuration, double rate) {
        double minutes = callDuration / 60.0;
        return minutes * rate;
    }

    public static double calculateTaxesAndSurcharges(double totalCost) {
        return totalCost * 0.1;
    }

    public static String generateRandomBillingPeriod(Random rand) {
        String[] billingPeriods = {"Monthly", "Prepaid", "Annually", "Quarterly", "Weekly", "Bi-Weekly", "Daily", "Postpaid"};
        String billingPeriod = billingPeriods[rand.nextInt(billingPeriods.length)];
        if ("Prepaid".equals(billingPeriod)) {
            return billingPeriod;
        } else {
            return billingPeriod;
        }
    }

    public static String generateRandomPaymentStatus(Random rand, String billingPeriod) {
        String[] paymentStatus = {"Paid", "Unpaid"};
        if (billingPeriod.equals("Prepaid")) {
            return "Paid";
        } else {
            return paymentStatus[rand.nextInt(paymentStatus.length)];
        }
    }
}
