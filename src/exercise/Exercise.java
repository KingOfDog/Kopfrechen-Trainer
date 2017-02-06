package exercise;

import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import exercise.calculator.Calculator;
import settings.Settings;

public class Exercise {

    private Calculator calculator;
    private int count;

    public Exercise(Calculator calculator, int count) {
        this.calculator = calculator;
        this.count = count;
    }

    public ExerciseSheet generate() {
        ExerciseSheet sheet = new ExerciseSheet();
        sheet.firstNumbers(calculator);
        for (int i = 0; i < count - 2; i++) {
            sheet.addNumber(calculator);
        }
        return sheet;
    }

}
