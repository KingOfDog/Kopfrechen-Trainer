package main;

public class Exercise {

	public String[] main() {
		String[] result = randomOperator();
		return result;
	}
	
	private String[] randomOperator() {
		int rand = (int)(Math.random() * 4);
		String[] result;
		if(rand == 0) {
			result = add();
		} else if(rand == 1) {
			result = sub();
		} else if(rand == 2) {
			result = mul();
		} else {
			result = div();
		}
		return result;
	}

	public static int[] random(int min, int max) {
		int number1 = min + (int)(Math.random() * ((max - min) + 1));
		int number2 = min + (int)(Math.random() * ((max - min) + 1));
		int[] numbers = {number1, number2};
		return numbers;
	}
	
	public static String[] add() {
		int[] numbers = random(0, 1000);
		Integer result = numbers[0] + numbers[1];
		String solution = numbers[0] + " + " + numbers[1] + " = " + result;
		String exercise = numbers[0] + " + " + numbers[1] + " = ?";
		String resultStr = result.toString();
		String[] resultArr = {resultStr, solution, exercise};
		return resultArr;
	}
	
	public static String[] sub() {
		int[] numbers = random(0, 1000);
		Integer result = numbers[0] - numbers[1];
		String solution = numbers[0] + " - " + numbers[1] + " = " + result;
		String exercise = numbers[0] + " - " + numbers[1] + " = ?";
		String resultStr = result.toString();
		String[] resultArr = {resultStr, solution, exercise};
		return resultArr;
	}
	
	public static String[] mul() {
		int[] numbers = random(0, 25);
		Integer result = numbers[0] * numbers[1];
		String solution = numbers[0] + " * " + numbers[1] + " = " + result;
		String exercise = numbers[0] + " * " + numbers[1] + " = ?";
		String resultStr = result.toString();
		String[] resultArr = {resultStr, solution, exercise};
		return resultArr;
	}
	
	public static String[] div() {
		int[] numbers = random(1, 25);
		Integer result;
		Integer number2;
		if(numbers[0] >= numbers[1]) {
			result = numbers[0];
			number2 = numbers[1];
		} else {
			result = numbers[1];
			number2 = numbers[0];
		}
		
		Integer number1 = result * number2;
		String solution = number1 + " / " + number2 + " = " + result;
		String exercise = number1 + " / " + number2 + " = ?";
		String resultStr = result.toString();
		String[] resultArr = {resultStr, solution, exercise};
		return resultArr;
	}
	
}
