package exercise.calculator;

import settings.Settings;

public class SubtractionCalculator implements Calculator {

	@Override
	public float calculate(float a, float b) {
		return a - b;
	}

	@Override
	public char getOperatorSign() {
		return '-';
	}

	@Override
	public int minimumBound() {
		return Settings.subMin;
	}

	@Override
	public int maximumBound() {
		return Settings.subMax;
	}

	@Override
	public boolean isValid(float a, float b) {
		if(!Settings.subNeg && a < b) return false;
		
		return true;
	}
	
}
