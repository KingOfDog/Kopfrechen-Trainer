package exercise.calculator;

import settings.Settings;

public class DivisionCalculator implements Calculator {

	private Settings settings = Settings.getInstance();

	@Override
	public double calculate(double a, float b) {
		return a / b;
	}

	@Override
	public String getOperatorSign() {
		return "÷";
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
	public boolean isValid(double a, float b) {
		if(!settings.divDec.getValue() && a % b != 0) return false;
		
		return true;
	}
	
}
