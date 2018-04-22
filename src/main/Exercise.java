package main;

import java.util.ArrayList;
import java.util.List;

import jsonFiles.Settings;

public class Exercise {

	public String[] main() {
		String[] result = randomOperator();
		return result;
	}
	
	private String[] randomOperator() {
		List<String[]> operators = new ArrayList<>();
		if(Settings.add) {
			operators.add(add());
		}
		if(Settings.sub) {
			operators.add(sub());
		}
		if(Settings.mul) {
			operators.add(mul());
		}
		if(Settings.div) {
			operators.add(div());
		}
		int rand = (int)(Math.random() * operators.size());
		String[] result = operators.get(rand);
		return result;
	}

	public static int[] random(int min, int max) {
		int number1 = min + (int)(Math.random() * ((max - min) + 1));
		int number2 = min + (int)(Math.random() * ((max - min) + 1));
		int[] numbers = {number1, number2};
		return numbers;
	}
	
	public static String[] add() {
		int[] numbers = random(Settings.addMin, Settings.addMax);
		Integer result = numbers[0] + numbers[1];
		String solution = numbers[0] + " + " + numbers[1] + " = " + result;
		String exercise = numbers[0] + " + " + numbers[1] + " = ?";
		String resultStr = result.toString();
		String[] resultArr = {resultStr, solution, exercise};
		return resultArr;
	}
	
	public static String[] sub() {
		int[] numbers = random(Settings.subMin, Settings.subMax);
		Integer result;
		String solution;
		String exercise;
		String resultStr;
		if(Settings.subNeg) {
			result = numbers[0] - numbers[1];
			solution = numbers[0] + " - " + numbers[1] + " = " + result;
			exercise = numbers[0] + " - " + numbers[1] + " = ?";
			resultStr = result.toString();
		} else {
			if(numbers[0] >= numbers[1]) {
				result = numbers[0] - numbers[1];
				solution = numbers[0] + " - " + numbers[1] + " = " + result;
				exercise = numbers[0] + " - " + numbers[1] + " = ?";
				resultStr = result.toString();
			} else {
				result = numbers[1] - numbers[0];
				solution = numbers[1] + " - " + numbers[0] + " = " + result;
				exercise = numbers[1] + " - " + numbers[0] + " = ?";
				resultStr = result.toString();
			}
		}
		String[] resultArr = {resultStr, solution, exercise};
		return resultArr;
	}
	
	public static String[] mul() {
		int[] numbers = random(Settings.mulMin, Settings.mulMax);
		Integer result = numbers[0] * numbers[1];
		String solution = numbers[0] + " * " + numbers[1] + " = " + result;
		String exercise = numbers[0] + " * " + numbers[1] + " = ?";
		String resultStr = result.toString();
		String[] resultArr = {resultStr, solution, exercise};
		return resultArr;
	}
	
	public static String[] div() {
		int[] numbers = random(Settings.divMin, Settings.divMax);
		Integer result;
		Integer number1;
		Integer number2;
		if(Settings.divComma) {
			System.out.println("Die erweiterte Divisions-Funktion ist noch nicht verfügbar!");
		}
		if(numbers[0] >= numbers[1]) {
			result = numbers[0];
			number2 = numbers[1];
		} else {
			result = numbers[1];
			number2 = numbers[0];
		}
		number1 = (int) (result * number2);
		
		String solution = number1 + " / " + number2 + " = " + result;
		String exercise = number1 + " / " + number2 + " = ?";
		String resultStr = String.valueOf(result);
		String[] resultArr = {resultStr, solution, exercise};
		return resultArr;
	}
	
}
