package jsonFiles;

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
	
	public static boolean subNeg = true;
	public static boolean divComma = false;
	
	public static Locale lang = new Locale("de", "DE");
	
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
	public void setLang(Locale lang) {
		Settings.lang = lang;
	}
}
