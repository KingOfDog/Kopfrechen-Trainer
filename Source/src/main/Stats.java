package main;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import jsonFiles.Statistics;

@SuppressWarnings("restriction")
public class Stats {

	public void start(Scene scene) {
		
		Label exercises = (Label) scene.lookup("#exercises");
		Label solved = (Label) scene.lookup("#exercisesSolved");
		Label mark = (Label) scene.lookup("#mark");
		Label markWords = (Label) scene.lookup("#markWords");
		
		int ex = Statistics.exercises;
		int exSo = Statistics.exercisesCorrect;
		double markPoints = Double.valueOf(exSo) / Double.valueOf(ex) * Double.valueOf(15);
		String markWord = Marks.main(markPoints);
		
		exercises.setText("Aufgaben: " + ex);
		solved.setText("Richtig gelöste Aufgaben: " + exSo);
		mark.setText("Notenpunkte: " + Double.toString(markPoints));
		markWords.setText("Note: " + markWord);
	}
	
}
