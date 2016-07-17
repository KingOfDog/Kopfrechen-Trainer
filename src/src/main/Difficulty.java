package main;

import jsonFiles.Settings;

public class Difficulty {
	
	public static double getDifficulty() {
		double add = addition();
		double sub = subtraction();
		double mul = multiplication();
		double div = division();
		double difficulty = (add + sub + mul + div) / 4;
		if(difficulty > 10) {
			difficulty = 10;
		}
		if(difficulty < 0) {
			difficulty = 0;
		}
		return difficulty;
	}
	
	private static double addition() {
		if(!Settings.add) {
			return 0;
		}
		double addBase = 1;
		double result = addBase * (Settings.addMax - Settings.addMin + 1) / 1000;
		return result;
	}
	
	private static double subtraction() {
		if(!Settings.sub) {
			return 0;
		}
		double subBase = 1;
		double result = subBase * (Settings.subMax - Settings.subMin + 1) / 1000;
		if(Settings.subNeg) {
			result *= 1.5;
		}
		return result;
	}
	
	private static double multiplication() {
		if(!Settings.mul) {
			return 0;
		}
		double mulBase = 1.5;
		double result = mulBase * (Settings.mulMax - Settings.mulMin + 1) / 30;
		return result;
	}
	
	private static double division() {
		if(!Settings.div) {
			return 0;
		}
		// TODO: Division Comma CHecking 
		double divBase = 1.5;
		double result = divBase * (Settings.divMax - Settings.divMin + 1) / 20;
		return result;
	}
}
