package generators;

import settings.Settings;

public class DifficultyGenerator {

	public static double getDifficulty() {
		return (addsub() + muldiv()) * factor() / 100;
	}

	private static double addsub() {
		double factor = 1.25 + subNeg();
		double result = (addition() + subtraction()) / 2 * factor;
		return result;
	}

	private static double addition() {
		if (!Settings.add)
			return 0;
		return (Settings.addMin + Settings.addMin) / 2;
	}

	private static double additionRange() {
		if (!Settings.add)
			return 0;
		return Settings.addMax - Settings.addMin;
	}

	private static double subtraction() {
		if (!Settings.sub)
			return 0;
		return (Settings.subMin + Settings.subMax) / 2;
	}

	private static double subtractionRange() {
		if (!Settings.sub)
			return 0;
		return Settings.subMax - Settings.subMin;
	}

	private static double subNeg() {
		if (Settings.subNeg)
			return 1.5;
		return 0;
	}

	private static double muldiv() {
		double factor = 1.5 + divDec();
		double result = (multiplication() + division()) / 2 * factor;
		return result;
	}

	private static double multiplication() {
		if (!Settings.mul)
			return 0;
		return (Settings.mulMin + Settings.mulMax) / 2;
	}

	private static double multiplicationRange() {
		if (!Settings.mul)
			return 0;
		return Settings.mulMax - Settings.mulMin;
	}

	private static double division() {
		if (!Settings.div)
			return 0;
		return (Settings.divMin + Settings.divMax) / 2;
	}

	private static double divisionRange() {
		if (!Settings.div)
			return 0;
		return Settings.divMax - Settings.divMin;
	}

	private static double divDec() {
		if (Settings.divComma)
			return 2;
		return 0;
	}

	private static double factor() {
		double averageRange = (additionRange() + subtractionRange() + multiplicationRange() + divisionRange()) / 4;
		double rangeFactor = 1;
		if (averageRange < 600) {
			rangeFactor = 1.5;
		} else if (averageRange <= 800) {
			rangeFactor = 2;
		} else if (averageRange <= 1000) {
			rangeFactor = 3;
		} else if (averageRange > 1000) {
			rangeFactor = 4;
		}
		double factorCount = Settings.factorCount / 2;
		return rangeFactor + factorCount;
	}
}
