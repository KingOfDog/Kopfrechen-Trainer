package settings;

import java.util.Locale;

public class Settings {
	public static boolean add = true;
	public static boolean sub = true;
	public static boolean mul = true;
	public static boolean div = true;
	
	public static int addMin = 1;
	public static int addMax = 1000;
	public static int subMin = 1;
	public static int subMax = 1000;
	public static int mulMin = 0;
	public static int mulMax = 25;
	public static int divMin = 1;
	public static int divMax = 25;
	
	public static int factorCount = 2;
	
	public static boolean subNeg = true;
	public static boolean divComma = false;
	
	public static Locale lang = new Locale("en", "US");
	
	public static boolean sounds = true;
	public static double volume = 1;
	
	public static boolean automaticUpdates = true;
	
	public static boolean startMaximized = false;
	public static double startWidth = 850;
	public static double startHeight = 550;
	
	public static String exerciseCorrect = "#2ecc71";
	public static String exerciseWrong = "#e74c3c";
	
	public Settings() {
		
	}
	
	public void setAdd(boolean create, int min, int max) {
		add = create;
		addMin = min;
		addMax = max;
	}
	public void setSub(boolean create, int min, int max, boolean neg) {
		sub = create;
		subMin = min;
		subMax = max;
		subNeg = neg;
	}
	public void setMul(boolean create, int min, int max) {
		mul = create;
		mulMin = min;
		mulMax = max;
	}
	public void setDiv(boolean create, int min, int max, boolean comma) {
		div = create;
		divMin = min;
		divMax = max;
		divComma = comma;
	}
	public void setFactorCount(int count) {
		factorCount = count;
	}
	public void setLang(Locale locale) {
		lang = locale;
	}
	public void setSounds(boolean activate, double vol) {
		sounds = activate;
		volume = vol;
	}
	public void setUpdates(boolean activate) {
		automaticUpdates = activate;
	}
	public void setWindowSize(boolean maximized, double width, double height) {
		startMaximized = maximized;
		startWidth = width;
		startHeight = height;
	}

	public void setColors(String correct, String wrong) {
		exerciseCorrect = correct;
		exerciseWrong = wrong;
	}
}
