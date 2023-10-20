package com.cdr.main.functionality;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.cdr.main.entity.MmsCdr;

public class MmsCdrGeneration {

	public MmsCdr generateMmsCDR() {

		Random rand = new Random();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();
		Date startDate = new Date(122, 0, 1);

		Date senderTimestamp = generateRandomDate(startDate, currentDate, rand);

		Date receiverTimestamp = generateReceiverTimestamp(senderTimestamp, rand);

		long timeDifference = receiverTimestamp.getTime() - senderTimestamp.getTime();

		String messageStatus;
		if (timeDifference > 5 * 60 * 1000) {
			messageStatus = "Failed";
		} else {
			messageStatus = "Completed";
		}

		String senderTimestampStr = dateFormat.format(senderTimestamp);
		String receiverTimestampStr = (messageStatus.equals("Failed")) ? "N/A" : dateFormat.format(receiverTimestamp);

		String messageId = generateRandomMessageId();
		String senderPhoneNumber = generateRandomIndianPhoneNumber(rand);
		String receiverPhoneNumber = generateRandomIndianPhoneNumber(rand);
		String messageDirection = (rand.nextBoolean()) ? "Outgoing" : "Incoming";
		String messageType = "MMS";
		String networkType = generateRandomNetworkType(rand);
		String contentType = generateRandomContentType(rand);
		int contentSize = generateRandomContentSize(contentType, rand);
		String contentFormat = generateRandomContentFormat(contentType, rand);

		System.out.println("Message ID: " + messageId);
		System.out.println("Sender Phone Number: " + senderPhoneNumber);
		System.out.println("Receiver Phone Number: " + receiverPhoneNumber);
		System.out.println("Message Direction: " + messageDirection);
		System.out.println("Message Type: " + messageType);
		System.out.println("Network Type: " + networkType);
		System.out.println("Content Type: " + contentType);
		System.out.println("Content Size: " + contentSize + " KB");
		System.out.println("Content Format: " + contentFormat);
		System.out.println("Sender Timestamp: " + senderTimestampStr);
		System.out.println("Receiver Timestamp: " + receiverTimestampStr);
		System.out.println("Message Status: " + messageStatus);
		System.out.println();

		MmsCdr object = new MmsCdr();

		object.setMessageId(messageId);
		object.setSenderPhoneNumber(senderPhoneNumber);
		object.setReceiverPhoneNumber(receiverPhoneNumber);
		object.setMessageDirection(messageDirection);
		object.setMessageType(messageType);
		object.setNetworkType(networkType);
		object.setContentType(contentType);
		object.setContentSize(contentSize);
		object.setContentFormat(contentFormat);
		object.setSenderTimestampStr(senderTimestampStr);
		object.setReceiverTimestampStr(receiverTimestampStr);
		object.setMessageStatus(messageStatus);

		return object;

	}

	public static Date generateRandomDate(Date startDate, Date endDate, Random rand) {
		long startMillis = startDate.getTime();
		long endMillis = endDate.getTime();
		long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
		return new Date(randomMillisSinceEpoch);
	}

	public static Date generateReceiverTimestamp(Date senderTimestamp, Random rand) {
		long senderMillis = senderTimestamp.getTime();
		long receiverMillis = senderMillis + rand.nextInt(10 * 60 * 1000);
		return new Date(receiverMillis);
	}

	public static String generateRandomMessageId() {
		return "MMS-" + System.currentTimeMillis();
	}

	public static String generateRandomIndianPhoneNumber(Random rand) {
		String[] prefixes = { "6", "7", "8", "9" };
		String phoneNumber = "+91" + prefixes[rand.nextInt(prefixes.length)];
		for (int i = 0; i < 8; i++) {
			phoneNumber += rand.nextInt(10);
		}
		return phoneNumber;
	}

	public static String generateRandomNetworkType(Random rand) {
		String[] networkTypes = { "5G", "4G", "Wi-Fi" };
		return networkTypes[rand.nextInt(networkTypes.length)];
	}

	public static String generateRandomContentType(Random rand) {
		String[] contentTypes = { "Image", "Audio", "Video" };
		return contentTypes[rand.nextInt(contentTypes.length)];
	}

	public static int generateRandomContentSize(String contentType, Random rand) {
		if (contentType.equals("Image")) {
			int[] sizeRange = { 5, 100, 500 };
			return sizeRange[rand.nextInt(sizeRange.length)];
		} else if (contentType.equals("Audio")) {
			int[] sizeRange = { 100, 500, 1000 };
			return sizeRange[rand.nextInt(sizeRange.length)];
		} else if (contentType.equals("Video")) {
			int[] sizeRange = { 500, 1000, 5000 };
			return sizeRange[rand.nextInt(sizeRange.length)];
		}
		return 0;
	}

	public static String generateRandomContentFormat(String contentType, Random rand) {
		if (contentType.equals("Image")) {
			String[] imageFormats = { "jpeg", "png", "gif" };
			return imageFormats[rand.nextInt(imageFormats.length)];
		} else if (contentType.equals("Audio")) {
			String[] audioFormats = { "mpeg", "amr" };
			return audioFormats[rand.nextInt(audioFormats.length)];
		} else if (contentType.equals("Video")) {
			String[] videoFormats = { "mp4", "3GPP" };
			return videoFormats[rand.nextInt(videoFormats.length)];
		}
		return "";
	}

	public static boolean isFailedMessage(Date senderTimestamp, Date receiverTimestamp) {
		long timeDifference = receiverTimestamp.getTime() - senderTimestamp.getTime();
		return timeDifference > 5 * 60 * 1000;
	}

}
