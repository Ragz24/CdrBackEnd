package com.cdr.main.functionality;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.cdr.main.entity.VoiceCdr;

public class VoiceCdrGeneration {

	public static VoiceCdr generateVoiceCDR() {

		Random rand = new Random();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date startDate = generateRandomDate(2022, new Date(), rand);

		String callerPhoneNumber = generateIndianPhoneNumber(rand);
		String receiverPhoneNumber = generateIndianPhoneNumber(rand);

		String[] callResults = { "Connected", "Busy", "Missed" };
		String callResult = callResults[rand.nextInt(callResults.length)];

		String callDirection = (callResult.equals("Connected")) ? "Outgoing" : "Incoming";

		int duration = (callResult.equals("Connected")) ? generateRandomCallDuration(rand) : 0;

		Date callStartTime = new Date(startDate.getTime() - (duration * 1000));
		Date callEndTime = startDate;

		if (callEndTime.before(callStartTime)) {
			callEndTime = new Date(callStartTime.getTime() + (duration * 1000));
		}

		String startTimeStr = dateFormat.format(callStartTime);
		String endTimeStr = dateFormat.format(callEndTime);

		String callID = callerPhoneNumber.substring(3) + "-" + startTimeStr.substring(0, 4)
				+ startTimeStr.substring(5, 7) + startTimeStr.substring(8, 10) + startTimeStr.substring(11, 13)
				+ startTimeStr.substring(14, 16) + startTimeStr.substring(17);

		VoiceCdr voiceCdr = new VoiceCdr();

		voiceCdr.setCallStartTime(startTimeStr);
		voiceCdr.setCallEndTime(endTimeStr);
		voiceCdr.setCallDurationMinutes(duration / 60);
		voiceCdr.setCallerNumber(callerPhoneNumber);
		voiceCdr.setRecipientNumber(receiverPhoneNumber);
		voiceCdr.setCallType("Voice");
		voiceCdr.setCallResult(callResult);
		voiceCdr.setCallID(callID);
		voiceCdr.setCallDirection(callDirection);

		return voiceCdr;
	}

	public static Date generateRandomDate(int startYear, Date endDate, Random rand) {
		long startMillis = new Date(startYear - 1900, 0, 1).getTime();
		long endMillis = endDate.getTime();
		long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
		return new Date(randomMillisSinceEpoch);
	}

	public static String generateIndianPhoneNumber(Random rand) {
		String[] prefixes = { "6", "7", "8", "9" };
		String phoneNumber = "+91" + prefixes[rand.nextInt(prefixes.length)];
		for (int i = 0; i < 8; i++) {
			phoneNumber += rand.nextInt(10);
		}
		return phoneNumber;
	}

	public static int generateRandomCallDuration(Random rand) {
		return rand.nextInt(7200 - 5) + 5;
	}
}
