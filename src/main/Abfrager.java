package main;


import com.jfoenix.controls.JFXTextField;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import jsonFiles.Statistics;
import resources.lang.Language;

public class Abfrager {
	
	public static String exercise = "";
	public static String solution = "";
	public static double result = 0.0;
	
	public static boolean isChecked = false;
	public static int exercises = 0;
	public static int correct = 0;
	public static long timePlayed = 0;
	
	public void start(Scene scene) {
		generateExercise();
		setExercise(scene);
	}
	
	public void setExercise(Scene scene) {
		Label labelExercise = (Label) scene.lookup("#exercise");
		Label title = (Label) scene.lookup("#labelTitle");
		Label subtitle = (Label) scene.lookup("#labelSubheader");
		JFXTextField input = (JFXTextField) scene.lookup("#answerInput");
		labelExercise.setText(exercise);
		labelExercise.setTextFill(Color.web("#292929"));
		title.setText(Language.get("test.title"));
		title.setTextFill(Color.web("#292929"));
		subtitle.setText(Language.get("test.subtitle"));
		input.setText("");
		isChecked = false;
		timePlayed = System.currentTimeMillis();
	}
	
	public static void generateExercise() {
		Exercise newExercise = new Exercise();
		String[] returned = newExercise.main();
		exercise = returned[2];
		solution = returned[1];
		result = Integer.valueOf(returned[0]);
	}
	
	public void check(Scene scene) throws Exception {
		timePlayed = System.currentTimeMillis() - timePlayed;
		Statistics.addMillisecondsPlayed(timePlayed);
		TextField input = (TextField) scene.lookup("#answerInput");
		Integer answer = Integer.valueOf(input.getText());
		Label labelExercise = (Label) scene.lookup("#exercise");
		labelExercise.setText(solution);
		Label title = (Label) scene.lookup("#labelTitle");
		Label title2 = (Label) scene.lookup("#labelSubheader");
		
		exercises++;
		Statistics.setExercises(Statistics.getExercises() + 1);
		
		if(answer == result) {
			Media sound = new Media(this.getClass().getResource("/resources/sounds/correct.mp3").toExternalForm());
			MediaPlayer mp = new MediaPlayer(sound);
			mp.play();
			labelExercise.setTextFill(Color.web("#2ecc71"));
			title.setText(Language.get("test.title.correct"));
			title.setTextFill(Color.web("#2ecc71"));
			title2.setText(Language.get("test.subtitle.correct"));
			correct++;
			Statistics.setExercisesCorrect(Statistics.getExercisesCorrect() + 1);
		} else {
			Media sound = new Media(this.getClass().getResource("/resources/sounds/wrong.mp3").toExternalForm());
			MediaPlayer mp = new MediaPlayer(sound);
			mp.play();
			labelExercise.setTextFill(Color.web("#e74c3c"));
			title.setText(Language.get("test.title.wrong"));
			title.setTextFill(Color.web("#e74c3c"));
			title2.setText(Language.get("test.subtitle.wrong"));
			
		}		
		SaveFiles.writeStats();
		
		Label exercisesCorrect = (Label) scene.lookup("#exercisesCorrect");
		exercisesCorrect.setText(correct + " / " + exercises);
		Label mark = (Label) scene.lookup("#mark");
		double ecp = ((double) correct / (double) exercises) * (double) 15;
		String note = Marks.main(ecp);
		
		mark.setText(note);
		
		isChecked = true;	
	}
	
	public void newExercise(Scene scene) {
		generateExercise();
		setExercise(scene);
	}
	
}
