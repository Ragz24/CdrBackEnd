package com.cdr.main.functionality;

import java.util.Random;
import java.util.UUID;

import com.cdr.main.entity.VoIPCdr;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VoIPCdrGeneration {
	public static VoIPCdr generateVoipCDR() {
		Random rand = new Random();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		Date startDate = generateRandomDate(2022, currentDate, rand);

		String callType = (rand.nextBoolean()) ? "Audio" : "Video";

		String callResult = generateRandomCallResult(rand);

		int callSetUpTime = Math.max(5, generateCallSetUpTime(callResult, rand));

		int callDuration = (callResult.equals("Connected")) ? generateCallDuration(callType, rand) : 0;

		Date startTimestamp = new Date(startDate.getTime() - (callDuration * 1000));

		Date endTimestamp = startDate;

		if (endTimestamp.before(startTimestamp)) {
			endTimestamp = new Date(startTimestamp.getTime() + (callDuration * 1000));
		}

		double packetLoss = (callResult.equals("Connected")) ? generateRandomPacketLoss(rand) : 0;
		int jitter = (callResult.equals("Connected")) ? generateRandomJitter(rand) : 0;
		int delay = (callResult.equals("Connected")) ? generateRandomDelay(rand) : 0;

		String callID = generateRandomCallID(startTimestamp);
		String callerIP = generateRandomIPAddress(rand);
		String calleeIP = generateRandomIPAddress(rand);
		String callDirection = (rand.nextBoolean()) ? "Outgoing" : "Incoming";
		String codec = (callType.equals("Audio")) ? generateRandomAudioCodec() : generateRandomVideoCodec();
		String callRecordingStatus = (rand.nextBoolean()) ? "Enabled" : "Disabled";
		String networkType = generateRandomNetworkType(rand);
		String callTerminationReason = generateRandomCallTerminationReason(rand, callType, callResult, callDuration);

		String startTimestampStr = dateFormat.format(startTimestamp);
		String endTimestampStr = dateFormat.format(endTimestamp);

		System.out.println("Call ID: " + callID);
		System.out.println("Caller IP Address: " + callerIP);
		System.out.println("Callee IP Address: " + calleeIP);
		System.out.println("Start Timestamp: " + startTimestampStr);
		System.out.println("End Timestamp: " + endTimestampStr);
		System.out.println("Call Duration: " + callDuration / 60 + " minutes");
		System.out.println("Quality Metrics - Packet Loss: " + packetLoss + "%");
		System.out.println("Quality Metrics - Jitter: " + jitter + " ms");
		System.out.println("Quality Metrics - Delay: " + delay + " ms");
		System.out.println("Call Type: " + callType);
		System.out.println("Call Direction: " + callDirection);
		System.out.println("Codec: " + codec);
		System.out.println("Call Recording Status: " + callRecordingStatus);
		System.out.println("Network Type: " + networkType);
		System.out.println("Call Set Up Time: " + callSetUpTime + " seconds");
		System.out.println("Call Termination Reason: " + callTerminationReason);
		System.out.println("Call result " + callResult);
		System.out.println();

		VoIPCdr voipCDR = new VoIPCdr();

		voipCDR.setCallID(callID);

		voipCDR.setCallerIP(callerIP);

		voipCDR.setCalleeIP(calleeIP);

		voipCDR.setStartTimestamp(startTimestampStr);

		voipCDR.setEndTimestamp(endTimestampStr);

		voipCDR.setCallDuration(callDuration / 60);

		voipCDR.setPacketLoss(packetLoss);

		voipCDR.setJitter(jitter);

		voipCDR.setDelay(delay);

		voipCDR.setCallType(callType);

		voipCDR.setCallDirection(callDirection);

		voipCDR.setCodec(codec);

		voipCDR.setCallRecordingStatus(callRecordingStatus);

		voipCDR.setNetworkType(networkType);

		voipCDR.setCallSetUpTime(callSetUpTime);

		voipCDR.setCallTerminationReason(callTerminationReason);

		voipCDR.setCallResult(callResult);

		return voipCDR;
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

	public static int generateCallSetUpTime(String callResult, Random rand) {
		if (callResult.equals("Connected")) {
			return rand.nextInt(55) + 5; 
		}
		return 0; 
	}

	public static int generateCallDuration(String callType, Random rand) {
		if (callType.equals("Audio")) {
			return rand.nextInt(7500) + 5; 
		} else {
			return rand.nextInt(11700) + 5; 
		}
	}

	public static double generateRandomPacketLoss(Random rand) {
		return rand.nextDouble() * 10.0;
	}

	public static int generateRandomJitter(Random rand) {
		return rand.nextInt(101);
	}

	public static int generateRandomDelay(Random rand) {
		return rand.nextInt(201);
	}

	public static String generateRandomIPAddress(Random rand) {
		StringBuilder ipAddress = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			ipAddress.append(rand.nextInt(256));
			if (i < 3) {
				ipAddress.append(".");
			}
		}
		return ipAddress.toString();
	}

	public static String generateRandomAudioCodec() {
		String[] audioCodecs = { "G.711", "G.722", "G.729", "OPUS", "iLBC" };
		Random rand = new Random();
		return audioCodecs[rand.nextInt(audioCodecs.length)];
	}

	public static String generateRandomVideoCodec() {
		String[] videoCodecs = { "H.264", "H.265", "VP8", "VP9", "AV1" };
		Random rand = new Random();
		return videoCodecs[rand.nextInt(videoCodecs.length)];
	}

	public static String generateRandomNetworkType(Random rand) {
		String[] networkTypes = { "Wi-Fi", "5G", "4G" };
		return networkTypes[rand.nextInt(networkTypes.length)];
	}

	public static String generateRandomCallTerminationReason(Random rand, String callType, String callResult,
			int callDuration) {
		if (callResult.equals("Missed") || callResult.equals("Busy")) {
			return "Timeout";
		}

		String[] terminationReasons = { "User-Hangup", "Network Failure", "Timeout" };

		if ((callType.equals("Audio") && callDuration > 7200) || (callType.equals("Video") && callDuration > 10800)) {
			return terminationReasons[2];
		}

		return terminationReasons[rand.nextInt(terminationReasons.length - 1)];
	}

	public static Date generateRandomDate(int startYear, Date endDate, Random rand) {
		long startMillis = new Date(startYear - 1900, 0, 1).getTime();
		long endMillis = endDate.getTime();
		long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
		return new Date(randomMillisSinceEpoch);
	}

	public static String generateRandomCallID(Date startTimestamp) {
		return "Call-" + (startTimestamp.getTime() % 1000);
	}
}
