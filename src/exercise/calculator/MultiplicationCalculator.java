package exercise.calculator;

import settings.Settings;

public class MultiplicationCalculator implements Calculator {

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
		return Settings.mulMin;
	}

	@Override
	public int maximumBound() {
		return Settings.mulMax;
	}

	@Override
	public boolean isValid(float a, float b) {
		return true;
	}
	
}
