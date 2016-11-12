package generators;

import settings.Settings;

public class DifficultyGenerator {

    private static Settings settings = Settings.getInstance();

    public static double getDifficulty() {
        return (addsub() + muldiv()) * factor() / 100;
    }

    private static double addsub() {
        double factor = 1.25 + subNeg();
        double result = (addition() + subtraction()) / 2 * factor;
        return result;
    }

    private static double addition() {
        if (!settings.add.getValue())
            return 0;
        return (settings.addMin.getValue() + settings.addMax.getValue()) / 2;
    }

    private static double additionRange() {
        if (!settings.add.getValue())
            return 0;
        return settings.addMax.getValue() - settings.addMin.getValue();
    }

    private static double subtraction() {
        if (!settings.sub.getValue())
            return 0;
        return (settings.subMin.getValue() + settings.subMax.getValue()) / 2;
    }

    private static double subtractionRange() {
        if (!settings.sub.getValue())
            return 0;
        return settings.subMax.getValue() - settings.subMin.getValue();
    }

    private static double subNeg() {
        if (settings.subNeg.getValue())
            return 1.5;
        return 0;
    }

    private static double muldiv() {
        double factor = 1.5 + divDec();
        double result = (multiplication() + division()) / 2 * factor;
        return result;
    }

    private static double multiplication() {
        if (!settings.mul.getValue())
            return 0;
        return (settings.mulMin.getValue() + settings.mulMax.getValue()) / 2;
    }

    private static double multiplicationRange() {
        if (!settings.mul.getValue())
            return 0;
        return settings.mulMax.getValue() - settings.mulMin.getValue();
    }

    private static double division() {
        if (!settings.div.getValue())
            return 0;
        return (settings.divMin.getValue() + settings.divMax.getValue()) / 2;
    }

    private static double divisionRange() {
        if (!settings.div.getValue())
            return 0;
        return settings.divMax.getValue() - settings.divMin.getValue();
    }

    private static double divDec() {
        if (settings.divDec.getValue())
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
        double factorCount = settings.factorCount.getValue() / 2;
        return rangeFactor + factorCount;
    }
}
