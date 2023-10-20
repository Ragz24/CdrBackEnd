package com.cdr.main.functionality;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.cdr.main.entity.LocationCdr;

public class LocationCdrGeneration {
	public static LocationCdr generateLocationCDR() {
		Random rand = new Random();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date currentDate = new Date();

		Date updateTimestamp = generateRandomDate(2022, currentDate, rand);
		String cellID = generateRandomCellID(rand);
		double latitude = generateRandomLatitude(rand);
		double longitude = generateRandomLongitude(rand);
		int locationAreaCode = generateRandomLocationAreaCode(rand);
		String roamingStatus = generateRandomRoamingStatus(rand);
		int signalStrength = generateRandomSignalStrength(rand);
		double locationAccuracy = generateRandomLocationAccuracy(rand);
		String locationSource = generateRandomLocationSource(rand);
		String locationMethod = generateRandomLocationMethod(rand);

		String imei = generateRandomIMEI(rand);

		System.out.print("IMEI: " + imei);
		System.out.println("Location Update Timestamp: " + updateTimestamp);
		System.out.println("Cell ID or Tower ID: " + cellID);
		System.out.println("Latitude: " + latitude);
		System.out.println("Longitude: " + longitude);
		System.out.println("Location Area Code: " + locationAreaCode);
		System.out.println("Roaming Status: " + roamingStatus);
		System.out.println("Signal Strength: " + signalStrength + " dBm");
		System.out.println("Location Accuracy: " + locationAccuracy + " meters");
		System.out.println("Location Source: " + locationSource);
		System.out.println("Location Method: " + locationMethod);

		LocationCdr locationCdr = new LocationCdr();
		locationCdr.setImei(imei);
		locationCdr.setUpdateTimestamp(dateFormat.format(updateTimestamp));
		locationCdr.setCellID(cellID);
		locationCdr.setLatitude(latitude);
		locationCdr.setLongitude(longitude);
		locationCdr.setLocationAreaCode(locationAreaCode);
		locationCdr.setRoamingStatus(roamingStatus);
		locationCdr.setSignalStrength(signalStrength);
		locationCdr.setLocationAccuracy(locationAccuracy);
		locationCdr.setLocationSource(locationSource);
		locationCdr.setLocationMethod(locationMethod);

		return locationCdr;

	}

	public static Date generateRandomDate(int startYear, Date endDate, Random rand) {
		long startMillis = new Date(startYear - 1900, 0, 1).getTime();
		long endMillis = endDate.getTime();
		long randomMillisSinceEpoch = startMillis + (long) (rand.nextDouble() * (endMillis - startMillis));
		return new Date(randomMillisSinceEpoch);
	}

	public static String generateRandomCellID(Random rand) {
		return "Cell" + rand.nextInt(10000);
	}

	public static double generateRandomLatitude(Random rand) {
		return -90.0 + (180.0 * rand.nextDouble());
	}

	public static double generateRandomLongitude(Random rand) {
		return -180.0 + (360.0 * rand.nextDouble());
	}

	public static int generateRandomLocationAreaCode(Random rand) {
		return rand.nextInt(10000);
	}

	public static String generateRandomRoamingStatus(Random rand) {
		return rand.nextBoolean() ? "Roaming" : "Home Network";
	}

	public static int generateRandomSignalStrength(Random rand) {
		return -120 + rand.nextInt(90);
	}

	public static double generateRandomLocationAccuracy(Random rand) {
		return rand.nextDouble() * 100;
	}

	public static String generateRandomLocationSource(Random rand) {
		String[] sources = { "GPS (Global Positioning System)", "Cellular Network", "Wi-Fi Positioning",
				"IP Geolocation" };
		return sources[rand.nextInt(sources.length)];
	}

	public static String generateRandomLocationMethod(Random rand) {
		String[] methods = { "A-GPS", "Cell-ID", "Trilateration", "Multilateration", "TDOA", "Other" };
		return methods[rand.nextInt(methods.length)];
	}

	public static String generateRandomIMEI(Random rand) {

		StringBuilder imeiBuilder = new StringBuilder(15);

		imeiBuilder.append(0 + rand.nextInt(10));

		for (int i = 0; i < 6; i++) {
			imeiBuilder.append(rand.nextInt(10));
		}

		for (int i = 0; i < 8; i++) {
			imeiBuilder.append(rand.nextInt(10));
		}

		return imeiBuilder.toString();
	}
}
