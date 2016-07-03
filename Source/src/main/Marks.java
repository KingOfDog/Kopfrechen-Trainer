package main;

public class Marks {
	public static String main(double ecp) {
		String note = "";
		if(ecp == 15) {
			note = "Sehr gut plus";
		} else if(ecp >= 14) {
			note = "Sehr gut";
		} else if(ecp >= 13) {
			note = "Sehr gut minus";
		} else if(ecp >= 12) {
			note = "Gut plus";
		} else if(ecp >= 11) {
			note = "Gut";
		} else if(ecp >= 10) {
			note = "Gut minus";
		} else if(ecp >= 9) {
			note = "Befriedigend plus";
		} else if(ecp >= 8) {
			note = "Befriedigend";
		} else if(ecp >= 7) {
			note = "Befriedigend minus";
		} else if(ecp >= 6) {
			note = "Ausreichend plus";
		} else if(ecp >= 5) {
			note = "Ausreichend";
		} else if(ecp >= 4) {
			note = "Ausreichend minus";
		} else if(ecp >= 3) {
			note = "Mangelhaft plus";
		} else if(ecp >= 2) {
			note = "Mangelhaft";
		} else if(ecp >= 1) {
			note = "Mangelhaft minus";
		} else if(ecp >= 0) {
			note = "Ungenügend";
		}
		return note;
	}
}
