package exercise.calculator;

import settings.Settings;

public class SubtractionCalculator implements Calculator {

	private Settings settings = Settings.getInstance();

	@Override
	public double calculate(double a, float b) {
		return a - b;
	}

	@Override
	public String getOperatorSign() {
		return "-";
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
	public boolean isValid(double a, float b) {
		if(!settings.subNeg.getValue() && a < b) return false;
		
		return true;
	}
	
}
