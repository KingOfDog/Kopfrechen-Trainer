package exercise.calculator;

import settings.Settings;

public class MultiplicationCalculator implements Calculator {

	private Settings settings = Settings.getInstance();

	@Override
	public float calculate(float a, float b) {
		return a * b;
	}

	@Override
	public char getOperatorSign() {
		return '×';
	}

	@Override
	public int minimumBound() {
		return settings.mulMin.getValue();
	}

	@Override
	public int maximumBound() {
		return settings.mulMax.getValue();
	}

	@Override
	public boolean isValid(float a, float b) {
		return true;
	}
	
}
