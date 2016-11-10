package statistics;

import java.security.MessageDigest;

public class Statistics {
	private static int exercises;
	private static int exercisesCorrect;
	private static long millisecondsPlayed;
	private static String session;
	private static double score;
	
	public Statistics() {
		// Nothing here
	}

	public static int getExercisesCorrect() {
		return exercisesCorrect;
	}

	public static void setExercisesCorrect(int exercisesCorrect) {
		Statistics.exercisesCorrect = exercisesCorrect;
	}

	public static int getExercises() {
		return exercises;
	}

	public static void setExercises(int exercises) {
		Statistics.exercises = exercises;
	}

	public static long getMillisecondsPlayed() {
		return millisecondsPlayed;
	}

	public static void setMillisecondsPlayed(long millisecondsPlayed) {
		Statistics.millisecondsPlayed = millisecondsPlayed;
	}
	
	public static void addMillisecondsPlayed(long millisecondsPlayed) {
		Statistics.millisecondsPlayed += millisecondsPlayed;
	}
	
	public static String generateChecksum() throws Exception {
		String sum = "Exercises:" + Statistics.exercises + "-Correct:" + Statistics.exercisesCorrect + "-Played:" + Statistics.millisecondsPlayed + "-Score:" + Statistics.score;
		return getMD5Checksum(sum);
	}
	
	public static void setSession(String cs) {
		Statistics.session = cs;
	}
	
	public static double getScore() {
		return score;
	}

	public static void setScore(double score) {
		Statistics.score = score;
	}
	
	public static void addScore(double score) {
		Statistics.score += score;
	}

	public static byte[] createChecksum(String string) throws Exception {
		byte[] stringBytes = string.getBytes();
		MessageDigest md = MessageDigest.getInstance("MD5");
		return md.digest(stringBytes);
	}

	public static String getMD5Checksum(String string) throws Exception {
		byte[] b = createChecksum(string);
		String result = "";
       
		for (int i=0; i < b.length; i++) {
			result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		}
		return result;
	}

	public static String getSession() {
		return Statistics.session;
	}
	
	public static void clear() {
		Statistics.exercises = 0;
		Statistics.exercisesCorrect = 0;
		Statistics.millisecondsPlayed = 0;
		Statistics.session = "";
	}
}