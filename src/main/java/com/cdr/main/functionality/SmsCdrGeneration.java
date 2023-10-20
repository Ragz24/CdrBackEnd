package com.cdr.main.functionality;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.cdr.main.entity.LocationCdr;
import com.cdr.main.entity.SmsCdr;

public class SmsCdrGeneration {
	public static SmsCdr generateSmsCDR() {

		Random rand = new Random();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String[] messages = {

				"Dear Customer, your verification code is: 753619. Please use this code to confirm your identity.",

				"Dear User, please use code 408572 to validate your email address. Your security is our priority.",

				"Meeting update: The location has been changed to Room 2A. See you there at 3 PM.",

				"Good morning! How's your day going at the office?",

				"Hey there! How's your day shaping up?",

				"Let's plan a picnic soon. What do you say?",

				"Remember that project we thought was impossible? We nailed it!",

				"I just saw the most beautiful sunset. Nature's artwork at its best.",

				"Thanks for being awesome and making my day brighter!",

				"So, what's the latest in your world?",

				"Dear Client, we're updating you on the board meeting. It's rescheduled to 2 PM in the conference room.",

				"Dear User, please note that tomorrow's presentation has been advanced to 10 AM. Be prepared accordingly.",

				"Important notice for our Customers: The quarterly review has been moved to next Tuesday at 3:30 PM.",

				"Dear Account Holder, your account balance is below the minimum threshold. Please fund your account.",

				"Dear Cardholder, your credit card payment is due in two days. Avoid late fees by making a payment.",

				"Dear Customer, an unusual transaction has been detected on your account. Please review and confirm its legitimacy.",

				"Dear Partner, explore new horizons of success with our innovative solutions. Connect with us for a consultation.",

				"Dear Professional, elevate your career with our professional development programs. Unlock your potential today.",

				"Dear Client, your success is our top priority. Trust us for quality service and excellence in every endeavor.",

				"Dear Business Owner, unleash your creativity and drive growth with our strategic partnership opportunities.",

				"Dear Valued Customer, promote your brand with us and witness unparalleled visibility and business growth.",

				"Good afternoon, Dear Customer! How's your day going at the office?",

				"Dear User, let's plan a strategic discussion over coffee next week. Are you available?",

				"Wishing you a productive day ahead, Dear Customer. Stay focused on your goals.",

				"Dear Client, what's on the agenda for the weekend? Any projects or relaxation planned?",

				"Dear Valued User, thank you for your support and professionalism. Your contributions are valued.",

				"Hey friend, just a quick hello to brighten your day. 🌞",

				"Planning to hit the gym later? Let's motivate each other!",

				"Pizza or sushi tonight? Let's decide our dinner plans.",

				"Why don't scientists trust atoms? Because they make up everything! 😄",

				"Can't find your keys again? They're on the kitchen counter.",

				"I just discovered a new favorite book. Got any reading recommendations?",

				"Thinking of going for a hike this weekend. Care to join?",

				"Feeling creative today? Let's brainstorm some new project ideas.",

				"Your support is like a warm cup of tea on a rainy day. Thanks for always being there!",

				"Lunch plans for tomorrow? How about trying that new place in town?",

				"Life's like a rollercoaster, full of ups and downs. How's your journey going?",

				"Weekend plans yet? A movie night sounds great!",

				"Remember our last road trip? We should plan another one soon.",

				"Just wanted to say you're a rockstar in my book! Keep shining bright!",

				"What's the craziest adventure you've ever been on?",

				"So, did you finally try that fancy restaurant? How was the food?",

				"They say laughter is the best medicine. Any funny stories to share?",

				"Pizza night with friends? Count me in!",

				"Dear friend, the world is a better place with you in it. Keep spreading positivity!",

				"Got any Netflix recommendations? I'm in need of a good show to binge-watch.",

				"Cup of coffee, a good book, and some me-time—perfect way to relax.",

				"Dear buddy, any exciting travel plans on the horizon?",

				"I believe in the power of dreams. What's your biggest dream?",

				"Weekend is just around the corner! Any special plans?",

				"Feeling grateful for great friends like you. 😊",

				"Let's make the most of this beautiful day. Got any outdoor plans?",

				"Feeling thankful for all the moments we've shared. Here's to more adventures!",

				"Just a friendly reminder: You're awesome! 🌟",

				"Hope you're having a fantastic day. Keep rocking!",

				"Looking back at old photos. Remember that incredible trip we took?",

				"Dear pal, how's life treating you lately?",

				"Pasta or pizza for dinner tonight? Tough choices!",

				"Random question: If you could have any superpower, what would it be?",

				"Dear friend, thank you for the joy you bring into my life. 🌈",

				"Busy day ahead? Don't forget to take a breather and enjoy the little things.",

				"Ever thought of trying your hand at a new hobby? Life's", };

		Date timestamp = generateRandomDate(2022, new Date(), rand);

		String senderNumber = generateIndianPhoneNumber(rand);
		String receiverNumber = generateIndianPhoneNumber(rand);

		String messageContent = messages[rand.nextInt(messages.length)];

		int messageLength = messageContent.length();

		String messageType = "SMS";
		String messageStatusResult = (rand.nextBoolean()) ? "Delivered" : "Failed";

		String messageDirectionResult = (rand.nextBoolean()) ? "Outgoing" : "Incoming";

		String messageID = generateMessageID(rand);

		String[] connectionTypes = { "4G LTE", "5G LTE", "3G LTE" };
		String connectionType = connectionTypes[rand.nextInt(connectionTypes.length)];

		String timestampStr = dateFormat.format(timestamp);

		String deliveryTimestampStr = (messageStatusResult.equals("Delivered"))
				? dateFormat.format(generateDeliveryTimeStamp(rand, timestamp))
				: "N/A";

		SmsCdr smsCdr = new SmsCdr();
		smsCdr.setTimeStamp(timestampStr);
		smsCdr.setDeliveryTimeStamp(deliveryTimestampStr);
		smsCdr.setSenderNumber(senderNumber);
		smsCdr.setReceiverNumber(receiverNumber);
		smsCdr.setMessageContent(messageContent);
		smsCdr.setMessageLength(messageLength);
		smsCdr.setMessageType(messageType);
		smsCdr.setMessageStatus(messageStatusResult);
		smsCdr.setMessageDirection(messageDirectionResult);
		smsCdr.setMessageID(messageID);
		smsCdr.setConnectionType(connectionType);

		return smsCdr;
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

	public static String generateMessageID(Random rand) {
		String firstPart = String.format("%010d", rand.nextInt(1000000000));
		String secondPart = String.format("%08d", rand.nextInt(100000000));
		String thirdPart = String.format("%06d", rand.nextInt(1000000));
		return firstPart + "-" + secondPart + "-" + thirdPart;
	}

	public static Date generateDeliveryTimeStamp(Random rand, Date timestamp) {
		long timestampMillis = timestamp.getTime();
		long deliveryTimestampMillis = timestampMillis + (rand.nextInt(5) + 1) * 60 * 1000;
		Date deliveryTimestamp = new Date(deliveryTimestampMillis);
		return deliveryTimestamp;
	}
}