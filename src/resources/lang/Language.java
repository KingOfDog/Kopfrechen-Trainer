package resources.lang;

import settings.Settings;

import java.util.ResourceBundle;

public class Language {

	private static Settings settings = Settings.getInstance();

	public static String get(String selector) {
		return ResourceBundle.getBundle("resources.lang.lang", settings.lang.getValue()).getString(selector);
	}
	
}
