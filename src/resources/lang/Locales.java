package resources.lang;

import java.util.Locale;

public enum Locales {

	GERMAN(new Locale("de", "DE"), "Deutsch"),
	ENGLISH(new Locale("en", "US"), "English"),
	FRENCH(new Locale("fr", "FR"), "Français"),
	CHINESE(new Locale("zh", "CN"), "中国"),
	RUSSIAN(new Locale("ru", "RU"), "Pусский"),
	SPAIN(new Locale("es", "ES"), "Español");
	
	private Locale locale;
	private String name;
	
	private Locales(Locale locale, String name) {
		this.locale = locale;
		this.name = name;
	}
	
	public Locale getLocale() {
		return locale;
	}
	
	public String getName() {
		return name;
	}
	
}
