package exercise.calculator;

import settings.Settings;

public class DivisionCalculator implements Calculator {

	@Override
	public float calculate(float a, float b) {
		return a / b;
	}

	@Override
	public char getOperatorSign() {
		return '÷';
	}

	@Override
	public int minimumBound() {
		return Settings.divMin;
	}

	@Override
	public int maximumBound() {
		return Settings.divMax;
	}

	@Override
	public boolean isValid(float a, float b) {
		if(!Settings.divComma && a % b != 0) return false;
		
		return true;
	}
	
}
