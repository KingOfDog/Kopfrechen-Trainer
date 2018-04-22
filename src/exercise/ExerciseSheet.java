package exercise;

import exercise.calculator.Calculator;
import exercise.calculator.PowerCalculator;

public class ExerciseSheet {
	
	double solution;
	StringBuilder exercise;

	public ExerciseSheet() {
		this.exercise = new StringBuilder();
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
		exercise.append(String.format("%.0f %s %.0f", a, calculator.getOperatorSign(), b));

		if(calculator.getClass() == PowerCalculator.class) {
		    exercise.append(((PowerCalculator) calculator).getClosingSign());
        }
	}
	
	public void addNumber(Calculator calculator) {
		float a = randomNumber(calculator);
		
		while(!calculator.isValid(solution, a)) {
			a = randomNumber(calculator);
		}
		
		solution = calculator.calculate(solution, a);
		exercise.append(String.format(" %s %.0f", calculator.getOperatorSign(), a));
	}
	
	public String getExercise() {
		return exercise.toString() + " = ???";
	}
	
	public String getExerciseSolution() {
		return exercise.toString() + " = " + solution;
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
