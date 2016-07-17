package jsonFiles;

import java.lang.reflect.Type;
import java.util.Locale;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class SettingsDes implements JsonDeserializer<Settings> {

	@Override
	public Settings deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		
		final boolean add = jsonObject.get("add").getAsBoolean();
		final boolean sub = jsonObject.get("sub").getAsBoolean();
		final boolean mul = jsonObject.get("mul").getAsBoolean();
		final boolean div = jsonObject.get("div").getAsBoolean();
		
		final int addMin = jsonObject.get("addMin").getAsInt();
		final int addMax = jsonObject.get("addMax").getAsInt();
		final int subMin = jsonObject.get("subMin").getAsInt();
		final int subMax = jsonObject.get("subMax").getAsInt();
		final int mulMin = jsonObject.get("mulMin").getAsInt();
		final int mulMax = jsonObject.get("mulMax").getAsInt();
		final int divMin = jsonObject.get("divMin").getAsInt();
		final int divMax = jsonObject.get("divMax").getAsInt();
		
		final boolean subNeg = jsonObject.get("subNeg").getAsBoolean();
		final boolean divComma = jsonObject.get("divComma").getAsBoolean();
		
		final String language = jsonObject.get("lang").getAsString();
		final Locale lang = new Locale(language.split("_")[0], language.split("_")[1]);
		
		final Settings settings = new Settings();
		settings.setAdd(add, addMin, addMax);
		settings.setSub(sub, subMin, subMax, subNeg);
		settings.setMul(mul, mulMin, mulMax);
		settings.setDiv(div, divMin, divMax, divComma);
		settings.setLang(lang);
		
		return settings;
	}

}
