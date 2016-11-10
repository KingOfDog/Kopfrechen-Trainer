package exercise.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface Calculator {
	
	public float calculate(float a, float b);
	
	public char getOperatorSign();
	
	public int minimumBound();
	
	public int maximumBound();
	
	public boolean isValid(float a, float b);
	
	public static Calculator getRandomCalculator(boolean add, boolean sub, boolean mul, boolean div) {
		List<Calculator> calculators = new ArrayList<>();
		if(add) {
			calculators.add(new AdditionCalculator());
		}
		if(sub) {
			calculators.add(new SubtractionCalculator());
		}
		if(mul) {
			calculators.add(new MultiplicationCalculator());
		}
		if(div) {
			calculators.add(new DivisionCalculator());
		}
		return calculators.get(new Random().nextInt(calculators.size()));
	}
	
}
