package main;


import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import jsonFiles.Statistics;

@SuppressWarnings("restriction")
public class Abfrager {
	
	public static String exercise = "";
	public static String solution = "";
	public static double result = 0.0;
	
	public static boolean isChecked = false;
	public static int exercises = 0;
	public static int correct = 0;
	
	public void start(Scene scene) {
		generateExercise();
		setExercise(scene);
	}
	
	public void setExercise(Scene scene) {
		Label labelExercise = (Label) scene.lookup("#exercise");
		Label title = (Label) scene.lookup("#labelTitle");
		Label subtitle = (Label) scene.lookup("#labelSubheader");
		TextField input = (TextField) scene.lookup("#answerInput");
		labelExercise.setText(exercise);
		labelExercise.setTextFill(Color.web("#292929"));
		title.setText("Löse die Aufgaben!");
		title.setTextFill(Color.web("#292929"));
		subtitle.setText("Viel Glück!");
		input.setText("");
		isChecked = false;
		
	}
	
	public static void generateExercise() {
		Exercise newExercise = new Exercise();
		String[] returned = newExercise.main();
		exercise = returned[2];
		solution = returned[1];
		result = Integer.valueOf(returned[0]);
	}
	
	public void check(Scene scene) {
		TextField input = (TextField) scene.lookup("#answerInput");
		Integer answer = Integer.valueOf(input.getText());
		Label labelExercise = (Label) scene.lookup("#exercise");
		labelExercise.setText(solution);
		Label title = (Label) scene.lookup("#labelTitle");
		Label title2 = (Label) scene.lookup("#labelSubheader");
		
		exercises++;
		Statistics.exercises += 1;
		
		if(answer == result) {
			labelExercise.setTextFill(Color.web("#2ecc71"));
			title.setText("Richtig!");
			title.setTextFill(Color.web("#2ecc71"));
			title2.setText("Gut gemacht!");
			correct++;
			Statistics.exercisesCorrect += 1;
		} else {
			labelExercise.setTextFill(Color.web("#e74c3c"));
			title.setText("Leider falsch!");
			title.setTextFill(Color.web("#e74c3c"));
			title2.setText("Das wird schon noch!");
			
		}		
		SaveFiles.writeStats();
		
		Label exercisesCorrect = (Label) scene.lookup("#exercisesCorrect");
		exercisesCorrect.setText(correct + " / " + exercises + " Aufgaben richtig gelöst");
		Label mark = (Label) scene.lookup("#mark");
		double ecp = ((double) correct / (double) exercises) * (double) 15;
		String note = Marks.main(ecp);
		
		mark.setText("Note: " + note);
		
		isChecked = true;	
	}
	
	public void newExercise(Scene scene) {
		generateExercise();
		setExercise(scene);
	}
	
}
