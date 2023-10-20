package com.cdr.main.functionality;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Random;

import com.cdr.main.entity.VideoCdr;

public class VideoCdrGeneration {

	public static VideoCdr generateVideoCDR() {
		Random rand = new Random();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		Date startDate = generateRandomDate(2022, currentDate, rand);

		VideoCdr videoCall = new VideoCdr();
		videoCall.setCallType(rand.nextBoolean() ? "One-to-One" : "Conference");
		videoCall.setCallerIdentifier(generateRandomPhoneNumber(rand));

		if (videoCall.getCallType().equals("One-to-One")) {
			String calleeIdentifier = generateRandomPhoneNumber(rand);
			videoCall.setCalleeIdentifier(calleeIdentifier);
		} else if (videoCall.getCallType().equals("Conference")) {
			int numCalleeIdentifiers = rand.nextInt(3) + 2;
			String calleeIdentifier = "";
			List<String> calleeIdentifiers = new ArrayList<>();
			for (int i = 0; i < numCalleeIdentifiers; i++) {
				calleeIdentifier += generateRandomPhoneNumber(rand) + " ";
			}
			videoCall.setCalleeIdentifier(calleeIdentifier);
		}

		videoCall.setCallStatus(generateRandomCallStatus(rand));

		int callDuration = generateRandomCallDuration(rand, videoCall.getCallStatus());

		Date startTimestamp = new Date(startDate.getTime() - (callDuration * 1000));
		Date endTimestamp = startDate;

		if (endTimestamp.before(startTimestamp)) {
			endTimestamp = new Date(startTimestamp.getTime() + (callDuration * 1000));
		}

		videoCall.setCallDuration(callDuration / 60);
		videoCall.setCallStartTimestamp(dateFormat.format(startTimestamp));
		videoCall.setCallEndTimestamp(dateFormat.format(endTimestamp));
		videoCall.setNetworkType(generateRandomNetworkType(rand));
		videoCall.setCallQualityMetrics(generateRandomQualityMetrics(rand));
		videoCall.setDeviceInformation(generateRandomDeviceInformation(rand));
		videoCall.setNetworkUsage(generateRandomNetworkUsage(rand));
		videoCall.setCodecInformation(generateRandomCodecInformation(rand));
		videoCall.setCallIdentifier(generateRandomCallIdentifier(rand));

		// Print the generated CDR
		System.out.println("Generated Video Call CDR:");
		System.out.println(videoCall);

		return videoCall;
	}

	public static String generateRandomCallIdentifier(Random rand) {
		char[] alphanumericChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		StringBuilder callIdentifier = new StringBuilder();
		for (int i = 0; i < 12; i++) {
			callIdentifier.append(alphanumericChars[rand.nextInt(alphanumericChars.length)]);
		}
		return callIdentifier.toString();
	}

	public static Date generateRandomDate(int startYear, Date endDate, Random rand) {
		long startMillis = new Date(startYear - 1900, 0, 1).getTime();
		long endMillis = endDate.getTime();
		long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
		return new Date(randomMillisSinceEpoch);
	}

	public static int generateRandomCallDuration(Random rand, String callStatus) {
		if (callStatus.equals("Missed") || callStatus.equals("Failed")) {

			return rand.nextInt(5) + 1;
		}

		return rand.nextInt(11700) + 5;

	}

	public static String generateRandomNetworkType(Random rand) {
		String[] networkTypes = { "4G", "5G", "Wi-Fi" };
		return networkTypes[rand.nextInt(networkTypes.length)];
	}

	public static String generateRandomQualityMetrics(Random rand) {
		int resolution = rand.nextInt(1920) + 1;
		int frameRate = rand.nextInt(31) + 1;
		int audioQuality = rand.nextInt(101);
		return String.format("Resolution: %dp, Frame Rate: %d FPS, Audio Quality: %d%%", resolution, frameRate,
				audioQuality);
	}

	public static String generateRandomCallStatus(Random rand) {
		String[] callStatuses = { "Completed", "Missed", "Failed" };
		return callStatuses[rand.nextInt(callStatuses.length)];
	}

	public static String generateRandomDeviceInformation(Random rand) {
	    String os = rand.nextBoolean() ? "iOS" : "Android";

	    if (os.equals("iOS")) {
	        String[] iphoneModels = { "iPhone 13", "iPhone 12", "iPhone 11", "iPhone XR", "iPhone SE", "iPhone X" };
	        String deviceModel = iphoneModels[rand.nextInt(iphoneModels.length)];
	        return String.format("Device Type: Smartphone, Model: %s, OS: %s", deviceModel, os);
	    } else {
	        String[] androidModels = { "Galaxy S21", "OnePlus 9", "Pixel 6", "Xperia 1 III", "Mi 11",
	                "ZenFone 8", "ROG Phone 5", "Find X3 Pro", "Mi 11X" };
	        String deviceModel = androidModels[rand.nextInt(androidModels.length)];
	        return String.format("Device Type: Smartphone, Model: %s, OS: %s", deviceModel, os);
	    }
	}


	public static String generateRandomPhoneNumber(Random rand) {
		String phoneNumber = "+";
		phoneNumber += "91 ";
		String[] prefixes = { "6", "7", "8", "9" };
		phoneNumber += prefixes[rand.nextInt(prefixes.length)];
		for (int i = 0; i < 8; i++) {
			phoneNumber += rand.nextInt(10);
		}
		return phoneNumber;
	}

	public static String generateRandomNetworkUsage(Random rand) {
		int dataTransmitted = rand.nextInt(1024 * 1024);
		int dataReceived = rand.nextInt(1024 * 1024);
		return String.format("Data Transmitted: %d KB, Data Received: %d KB", dataTransmitted, dataReceived);
	}

	public static String generateRandomCodecInformation(Random rand) {
		String[] videoCodecs = { "H.264", "H.265", "VP9" };
		String[] audioCodecs = { "AAC", "Opus", "PCM" };
		String videoCodec = videoCodecs[rand.nextInt(videoCodecs.length)];
		String audioCodec = audioCodecs[rand.nextInt(audioCodecs.length)];
		return String.format("Video Codec: %s, Audio Codec: %s", videoCodec, audioCodec);
	}

}
