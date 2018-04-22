package exercise.calculator;

import settings.Settings;

public class PowerCalculator implements Calculator {

	private Settings settings = Settings.getInstance();

	@Override
	public double calculate(double a, float b) {
		return Math.pow(a, b);
	}

	@Override
	public String getOperatorSign() {
		return "^";
	}

	public String getClosingSign() {
		return "";
	}

	@Override
	public int minimumBound() {
		return settings.powMinBase.getValue();
	}

	@Override
	public int maximumBound() {
		return settings.powMaxBase.getValue();
		}

	public int minimumBoundExpo() {
		return settings.powMinExpo.getValue();
	}

	public int maximumBoundExpo() {
		return settings.powMaxExpo.getValue();
	}

	@Override
	public boolean isValid(double a, float b) {
		return true;
	}
	
}
