package exercise;

import exercise.calculator.Calculator;
import settings.Settings;

public class Exercise {

	private Calculator calculator;
	private int count;
	
	public Exercise() {
		this(Calculator.getRandomCalculator(Settings.add, Settings.sub, Settings.mul, Settings.div), 2 + (int)(Math.random() * ((Settings.factorCount - 2) + 1)));
	}
	
	public Exercise(Calculator calculator, int count) {
		this.calculator = calculator;
		this.count = count;
	}
	
	public ExerciseSheet generate() {
		ExerciseSheet sheet = new ExerciseSheet();
		sheet.firstNumbers(calculator);
		for(int i = 0; i < count - 2; i++) {
			sheet.addNumber(calculator);
		}
		return sheet;
	}
	
}
