package com.cdr.main.functionality;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.cdr.main.entity.InternationalRoamingCdr;

public class InternationRoamingCdrGeneration {

    public static InternationalRoamingCdr generateRoamingCDR() {
        Random rand = new Random();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        Date startDate = generateRandomDate(2022, currentDate, rand);

        String cdrType = "Roaming";

        String callerNetwork = generateRandomNetwork(rand);
        String callerIMSI = generateRandomIMSI("404", generateRandomMNC(callerNetwork));
        String callerMSISDN = generateRandomMSISDN(rand);
        String callerLocation = generateRandomCallerLocation(rand);
        String callerCurrentNetwork = generateCallerNetwork(callerLocation, rand);

        String calleeLocation = generateCalleeLocation(rand);
        String calleeNumber = generateRandomCalleeNumber(calleeLocation, rand);
        String calleeNetwork = "Visited Network " + generateCalleeNetwork(calleeLocation, rand);
        String roamingPartner = generateRoamingPartner(callerNetwork, calleeLocation, rand);

        String callType = (rand.nextBoolean()) ? "Outgoing" : "Incoming";

        String callResult = generateRandomCallResult(rand);
        int callDuration = generateCallDuration(rand, callResult);
        String callTerminationReason = generateCallTerminationReason(callResult);

        String roamingCharges = calculateRandomCharges(roamingPartner, callerCurrentNetwork, rand, callTerminationReason);

        Date startTimestamp = new Date(startDate.getTime() - (callDuration * 1000));
        Date endTimestamp = startDate;

        if (endTimestamp.before(startTimestamp)) {
            endTimestamp = new Date(startTimestamp.getTime() + (callDuration * 1000));
        }

        System.out.println("CDR Type: " + cdrType);
        System.out.println("Call Start Timestamp: " + dateFormat.format(startTimestamp));
        System.out.println("Call End Timestamp: " + dateFormat.format(endTimestamp));
        System.out.println("Caller's IMSI: " + callerIMSI);
        System.out.println("Caller's MSISDN: " + callerMSISDN);
        System.out.println("Caller's Network: " + callerNetwork);
        System.out.println("Caller's Location: " + callerLocation);
        System.out.println("Caller's Network: " + callerCurrentNetwork);
        System.out.println("Callee's Number: " + calleeNumber);
        System.out.println("Callee's Location: " + calleeLocation);
        System.out.println("Callee's Network: " + calleeNetwork);
        System.out.println("Call Type: " + callType);
        System.out.println("Call Result: " + callResult);
        System.out.println("Call Termination Reason: " + callTerminationReason);
        System.out.println("Call Duration : " + callDuration / 60 + " minutes");
        System.out.println("Roaming Partner : " + roamingPartner);
        System.out.println("RoamingCharges: " + roamingCharges);
        System.out.println();

        startDate = endTimestamp;

        InternationalRoamingCdr cdr = new InternationalRoamingCdr();
        cdr.setCdrType("Roaming");
        cdr.setStartTimestamp(dateFormat.format(startTimestamp));
        cdr.setEndTimestamp(dateFormat.format(endTimestamp));
        cdr.setCallerIMSI(callerIMSI);
        cdr.setCallerMSISDN(callerMSISDN);
        cdr.setCallerNetwork(callerNetwork);
        cdr.setCallerLocation(callerLocation);
        cdr.setCallerCurrentNetwork(callerCurrentNetwork);
        cdr.setCalleeNumber(calleeNumber);
        cdr.setCalleeLocation(calleeLocation);
        cdr.setCalleeNetwork(calleeNetwork);
        cdr.setCallType(callType);
        cdr.setCallResult(callResult);
        cdr.setCallTerminationReason(callTerminationReason);
        cdr.setCallDuration(callDuration / 60);
        cdr.setRoamingPartner(roamingPartner);
        cdr.setRoamingCharges(roamingCharges);

        return cdr;
    }

    public static String generateCallTerminationReason(String callResult) {
        if (callResult.equals("Missed") || callResult.equals("Busy")) {
            return "Timeout";
        } else {
            String[] terminationReasons = {"User-Hangup", "Network Failure"};
            Random rand = new Random();
            return terminationReasons[rand.nextInt(terminationReasons.length)];
        }
    }

    public static int generateCallDuration(Random rand,String callResult) 
    {  if(callResult.equals("Missed")||callResult.equals("Busy"))
        return 0;
        return rand.nextInt(7155) + 45; 
    }

    public static String generateRandomNetwork(Random rand) {
        String[] networks = {"JIO", "Airtel", "Vodafone"};
        return networks[rand.nextInt(networks.length)];
    }

    public static String generateRandomIMSI(String mcc, String mnc) {
        String msin = generateRandomMSIN();
        return mcc + mnc + msin;
    }

    public static String generateRandomMNC(String callerNetwork) {
        if (callerNetwork.equals("Airtel")) {
            return "103";
        } else if (callerNetwork.equals("JIO")) {
            return "404";
        } else {
            return "405";
        }
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

    public static String generateRandomMSIN() {
        Random rand = new Random();
        long msinNumber = Math.round(rand.nextDouble() * 9e9) + (long) 1e9;
        return String.valueOf(msinNumber);

    }

    public static String generateRandomCallerLocation(Random rand) {
        int locationCode = rand.nextInt(7); 
        String location="Roaming in New York, USA";

        switch (locationCode) 
        {
            case 0:
                location = "Roaming in New York, USA";
                break;
            case 1:
                location = "Roaming in Los Angeles, USA";
                break;
            case 2:
                location = "Roaming in Chicago, USA";
                break;
            case 3:
                location = "Roaming in London, UK";
                break;
            case 4:
                location = "Roaming in Manchester, UK";
                break;
            case 5:
                location = "Roaming in Birmingham, UK";
                break;
            case 6:
                location = "Roaming in Glasgow, UK";
                break;
        }

        return location;
    }

    public static String generateRandomCalleeNumber(String calleeLocation, Random rand) {
        if (calleeLocation.contains("UK")) {
            return generateRandomUKPhoneNumber(rand);
        } else if (calleeLocation.contains("India")) {
            return generateRandomIndianPhoneNumber(rand);
        } else if (calleeLocation.contains("USA")) {
            return generateRandomUSPhoneNumber(rand);
        } else {
            return "";
        }
    }

    public static String generateRoamingPartner(String callerNetwork, String calleeLocation, Random rand) {
        if (callerNetwork.equals("JIO")) {
            if (calleeLocation.contains("UK")) {
                return "Hutchison";
            } else if (calleeLocation.contains("USA")) {
                return "Verizon";
            } else {
                return "JIO";
            }
        } else if (callerNetwork.equals("Vodafone")) {
            if (calleeLocation.contains("UK")) {
                return "Vodafone";
            } else if (calleeLocation.contains("USA")) {
                return "TMO";
            } else {
                return "Vodafone";
            }
        } else if (callerNetwork.equals("Airtel")) {
            if (calleeLocation.contains("UK")) {
                return "EE";
            } else if (calleeLocation.contains("USA")) {
                return "AT&T";
            } else {
                return "Airtel";
            }
        }
        return "No Roaming Partner Found";
    }



    public static String generateRandomIndianPhoneNumber(Random rand) {
        String[] prefixes = {"6", "7", "8", "9"};
        String phoneNumber = "+91" + prefixes[rand.nextInt(prefixes.length)];
        for (int i = 0; i < 8; i++) {
            phoneNumber += rand.nextInt(10);
        }
        return phoneNumber;
    }

    public static String generateRandomUKPhoneNumber(Random rand) {
        String phoneNumber = "+44 ";
        phoneNumber += rand.nextInt(10); 
        for (int i = 0; i < 10; i++) {
            phoneNumber += rand.nextInt(10);
            if (i == 4 || i == 7) {
                phoneNumber += " ";
            }
        }
        return phoneNumber;
    }

    public static String generateRandomUSPhoneNumber(Random rand) {
        String phoneNumber = "+1 (";
        for (int i = 0; i < 3; i++) {
            phoneNumber += rand.nextInt(10);
        }
        phoneNumber += ") ";
        for (int i = 0; i < 3; i++) {
            phoneNumber += rand.nextInt(10);
        }
        phoneNumber += "-";
        for (int i = 0; i < 4; i++) {
            phoneNumber += rand.nextInt(10);
        }
        return phoneNumber;
    }

    public static String generateCalleeLocation(Random rand) {
        int locationCode = rand.nextInt(10); // Generates a random number between 0 and 9
        String location;

        switch (locationCode) {
            case 0:
                location = "Visiting Network in New York, USA";
                break;
            case 1:
                location = "Visiting Network in Los Angeles, USA";
                break;
            case 2:
                location = "Visiting Network in Chicago, USA";
                break;
            case 3:
                location = "Visiting Network in London, UK";
                break;
            case 4:
                location = "Visiting Network in Manchester, UK";
                break;
            case 5:
                location = "Visiting Network in Birmingham, UK";
                break;
            case 6:
                location = "Visiting Network in Glasgow, UK";
                break;
            case 7:
                location = "Visiting Network in New Delhi, India";
                break;
            case 8:
                location = "Visiting Network in Chennai, India";
                break;
            case 9:
                location = "Visiting Network in Mumbai, India";
                break;
            default:
                location = "Unknown Location";
        }

        return location;
    }

    public static String generateCalleeNetwork(String calleeLocation, Random rand) {
        if (calleeLocation.contains("UK")) {
            String[] ukNetworks = {"Hutchison", "Vodafone", "EE", "O2"};
            return ukNetworks[rand.nextInt(ukNetworks.length)];
        } else if (calleeLocation.contains("USA")) {
            String[] usaNetworks = {"Verizon", "TMO", "AT&T"};
            return usaNetworks[rand.nextInt(usaNetworks.length)];
        } else if (calleeLocation.contains("India")) {
            String[] indiaNetworks = {"JIO", "Vodafone", "Airtel"};
            return indiaNetworks[rand.nextInt(indiaNetworks.length)];
        } else {
            return "Unknown Network";
        }
    } 
    
     public static String generateCallerNetwork(String callerLocation, Random rand) {
        if (callerLocation.contains("UK")) {
            String[] ukNetworks = {"Hutchison", "Vodafone", "EE", "O2"};
            return ukNetworks[rand.nextInt(ukNetworks.length)];
        } else if (callerLocation.contains("USA")) {
            String[] usaNetworks = {"Verizon", "TMO", "AT&T"};
            return usaNetworks[rand.nextInt(usaNetworks.length)];
        } else if (callerLocation.contains("India")) {
            String[] indiaNetworks = {"JIO", "Vodafone", "Airtel"};
            return indiaNetworks[rand.nextInt(indiaNetworks.length)];
        } else {
            return "Unknown Network";
        }
    }

    public static Date generateRandomDate(int startYear, Date endDate, Random rand) {
        long startMillis = new Date(startYear - 1900, 0, 1).getTime();
        long endMillis = endDate.getTime();
        long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
        return new Date(randomMillisSinceEpoch);
    } 
    public static String generateRandomCallResult(Random rand) {
        int result = rand.nextInt(3); 
        switch (result) {
            case 0:
                return "Connected";
            case 1:
                return "Missed";
            case 2:
                return "Busy";
            default:
                return "Connected"; 
        }
    }

    public static String calculateRandomCharges(String roamingPartner, String callerCurrentNetwork, Random rand, String callTerminationReason) {
        double roamingCharge = 0.0;
        double localCharge = 0.0;
        double totalCharge = 0.0;
        
        if (!callTerminationReason.equals("Timeout")) {
            if (roamingPartner.equals(callerCurrentNetwork)) {
                roamingCharge = 0.0;
                localCharge = 0.0;
            } else {
                roamingCharge = rand.nextDouble() * 50; 
                localCharge = rand.nextDouble() * 25;   
            }
        }
        
        totalCharge = roamingCharge + localCharge;

        String chargeString = String.format("Roaming Charge: $%.2f\nLocal Charge: $%.2f\nTotal Charge: $%.2f", roamingCharge, localCharge, totalCharge);


        return chargeString;
    }



}
