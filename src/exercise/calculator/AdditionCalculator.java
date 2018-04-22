package exercise.calculator;

import settings.Settings;

public class AdditionCalculator implements Calculator {

	private Settings settings = Settings.getInstance();

	@Override
	public double calculate(double a, float b) {
		return a + b;
	}

	@Override
	public String getOperatorSign() {
		return "+";
	}

	@Override
	public int minimumBound() {
		return settings.addMin.getValue();
	}

	@Override
	public int maximumBound() {
		return settings.addMax.getValue();
	}

	@Override
	public boolean isValid(double a, float b) {
		return true;
	}
	
}
