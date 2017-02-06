package exercise;

import exercise.calculator.Calculator;

public class ExerciseSheet {
	
	float solution;
	StringBuilder exercise;

	public ExerciseSheet() {
		this.exercise = new StringBuilder();
	}
	
	public void firstNumbers(Calculator calculator) {
		float a = randomNumber(calculator);
		float b = randomNumber(calculator);
		
		while(!calculator.isValid(a, b)) {
			a = randomNumber(calculator);
			b = randomNumber(calculator);
		}
		
		solution = calculator.calculate(a, b);
		exercise.append(String.format("%.0f %c %.0f", a, calculator.getOperatorSign(), b));
	}
	
	public void addNumber(Calculator calculator) {
		float a = randomNumber(calculator);
		
		while(!calculator.isValid(solution, a)) {
			a = randomNumber(calculator);
		}
		
		solution = calculator.calculate(solution, a);
		exercise.append(String.format(" %c %.0f", calculator.getOperatorSign(), a));
	}
	
	public String getExercise() {
		return exercise.toString() + " = ???";
	}
	
	public String getExerciseSolution() {
		return exercise.toString() + " = " + solution;
	}
	
	public float getSolution() {
		return solution;
	}
	
	public static float randomNumber(Calculator calc) {
		return calc.minimumBound() + (int)(Math.random() * ((calc.maximumBound() - calc.minimumBound()) + 1));
	}
	
}
