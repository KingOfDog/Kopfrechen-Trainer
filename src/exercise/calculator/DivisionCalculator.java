package exercise.calculator;

import settings.Settings;

public class DivisionCalculator implements Calculator {

	private Settings settings = Settings.getInstance();

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
		return settings.divMin.getValue();
	}

	@Override
	public int maximumBound() {
		return settings.divMax.getValue();
	}

	@Override
	public boolean isValid(float a, float b) {
		if(!settings.divDec.getValue() && a % b != 0) return false;
		
		return true;
	}
	
}
