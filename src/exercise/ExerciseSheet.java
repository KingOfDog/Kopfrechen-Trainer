package exercise;

import exercise.calculator.Calculator;
import exercise.calculator.PowerCalculator;
import javafx.geometry.VPos;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.time.temporal.Temporal;
import java.time.temporal.UnsupportedTemporalTypeException;

public class ExerciseSheet {
	
	double solution;
	TextFlow exercise;

	public ExerciseSheet() {
		this.exercise = new TextFlow();
	}
	
	public void firstNumbers(Calculator calculator) {
		float a = randomNumber(calculator);
		float b = randomNumber(calculator);
		if(calculator.getClass() == PowerCalculator.class) {
			b = randomExpo((PowerCalculator) calculator);
		}

		while(!calculator.isValid(a, b)) {
			a = randomNumber(calculator);
			b = randomNumber(calculator);
		}
		
		solution = calculator.calculate(a, b);
		Text part1;
		Text part2 = new Text();
		if(calculator.getClass() == PowerCalculator.class) {
		    part1 = new Text(String.format("%.0f", a));
            part2 = new Text(String.format("%.0f", b));
            part2.setStyle(((PowerCalculator) calculator).getClosingStyle());
            part2.setTranslateY(-12.0);
        } else {
            part1 = new Text(String.format("%.0f %s %.0f", a, calculator.getOperatorSign(), b));
        }

        this.exercise.getChildren().addAll(part1, part2);
	}
	
	public void addNumber(Calculator calculator) {
		float a = randomNumber(calculator);
		
		while(!calculator.isValid(solution, a)) {
			a = randomNumber(calculator);
		}
		
		solution = calculator.calculate(solution, a);

		Text text = new Text(String.format(" %s %.0f", calculator.getOperatorSign(), a));
		this.exercise.getChildren().add(text);
	}
	
	public TextFlow getExercise() {
	    TextFlow tempExercise = exercise;
        Text endText = new Text(" = ???");
        tempExercise.getChildren().add(endText);
		return tempExercise;
	}
	
	public TextFlow getExerciseSolution() {
	    TextFlow tempExercise = exercise;
        tempExercise.getChildren().remove(tempExercise.getChildren().size() - 1);
	    Text solutionText = new Text(" = " + solution);
        tempExercise.getChildren().add(solutionText);
		return tempExercise;
	}
	
	public double getSolution() {
		return solution;
	}
	
	private static float randomNumber(Calculator calc) {
		return calc.minimumBound() + (int)(Math.random() * ((calc.maximumBound() - calc.minimumBound()) + 1));
	}

	private static float randomExpo(PowerCalculator calc) {
		return calc.minimumBoundExpo() + (int)(Math.random() * ((calc.maximumBoundExpo() - calc.minimumBoundExpo()) + 1));
	}
	
}
