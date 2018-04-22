package main;

public class Marks {
	public static String main(double ecp) {
		String note = "";
		if(ecp >= 15) {
			note = "Sehr gut plus (1+)";
		} else if(ecp >= 14) {
			note = "Sehr gut (1)";
		} else if(ecp >= 13) {
			note = "Sehr gut minus (1-)";
		} else if(ecp >= 12) {
			note = "Gut plus (2+)";
		} else if(ecp >= 11) {
			note = "Gut (2)";
		} else if(ecp >= 10) {
			note = "Gut minus (2-)";
		} else if(ecp >= 9) {
			note = "Befriedigend plus (3+)";
		} else if(ecp >= 8) {
			note = "Befriedigend (3)";
		} else if(ecp >= 7) {
			note = "Befriedigend minus (3-)";
		} else if(ecp >= 6) {
			note = "Ausreichend plus (4+)";
		} else if(ecp >= 5) {
			note = "Ausreichend (4)";
		} else if(ecp >= 4) {
			note = "Ausreichend minus (4-)";
		} else if(ecp >= 3) {
			note = "Mangelhaft plus (5+)";
		} else if(ecp >= 2) {
			note = "Mangelhaft (5)";
		} else if(ecp >= 1) {
			note = "Mangelhaft minus (5-)";
		} else if(ecp >= 0) {
			note = "Ungenügend (6)";
		}
		return note;
	}
}
