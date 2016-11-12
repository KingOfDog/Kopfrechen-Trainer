package exercise.calculator;

import settings.Settings;

public class SubtractionCalculator implements Calculator {

	private Settings settings = Settings.getInstance();

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
		return settings.subMin.getValue();
	}

	@Override
	public int maximumBound() {
		return settings.subMax.getValue();
	}

	@Override
	public boolean isValid(float a, float b) {
		if(!settings.subNeg.getValue() && a < b) return false;
		
		return true;
	}
	
}
