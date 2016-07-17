package main;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import jsonFiles.Statistics;

public class Stats {

	public void start(Scene scene) {
		
		Label exercises = (Label) scene.lookup("#exercises");
		Label solved = (Label) scene.lookup("#exercisesSolved");
		Label mark = (Label) scene.lookup("#mark");
		Label markWords = (Label) scene.lookup("#markWords");
		Label time = (Label) scene.lookup("#timePlayed");
		
		int ex = Statistics.getExercises();
		int exSo = Statistics.getExercisesCorrect();
		double markPoints = Double.valueOf(exSo) / Double.valueOf(ex) * Double.valueOf(15);
		String markWord = Marks.main(markPoints);
		long timePlayed = Statistics.getMillisecondsPlayed();
		long second = (timePlayed / 1000) % 60;
		long minute = (timePlayed / (1000 * 60)) % 60;
		long hour = (timePlayed / (1000 * 60 * 60)) % 24;
		
		String timePlayedStr = String.format("%02d:%02d:%02d", hour, minute, second);
		
		exercises.setText(String.valueOf(ex));
		solved.setText(String.valueOf(exSo));
		mark.setText(String.valueOf(markPoints));
		markWords.setText(markWord);
		time.setText(timePlayedStr);
	}
	
}
