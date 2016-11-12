package settings;

import java.lang.reflect.Type;
import java.util.Locale;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class SettingsDeserializer implements JsonDeserializer<Settings> {

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
		final boolean divDec = jsonObject.get("divDec").getAsBoolean();

        final int factorCount = jsonObject.get("factorCount").getAsInt();

        final String language = jsonObject.get("lang").getAsString();
        final Locale lang = new Locale(language.split("_")[0], language.split("_")[1]);

//        final boolean sounds = jsonObject.get("sounds").getAsBoolean();
//        final double volume = jsonObject.get("volume").getAsDouble();

//        final boolean updates = jsonObject.get("automaticUpdates").getAsBoolean();

//        final boolean startMaximized = jsonObject.get("startMaximized").getAsBoolean();
//        final double startWidth = jsonObject.get("startWidth").getAsDouble();
//        final double startHeight = jsonObject.get("startHeight").getAsDouble();

//        final String exerciseCorrect = jsonObject.get("exerciseCorrect").getAsString();
//        final String exerciseWrong = jsonObject.get("exerciseWrong").getAsString();

        final Settings settings = Settings.getInstance();
        settings.add.setValue(add);
        settings.sub.setValue(sub);
        settings.mul.setValue(mul);
        settings.div.setValue(div);

        settings.addMin.setValue(addMin);
        settings.addMax.setValue(addMax);
        settings.subMin.setValue(subMin);
        settings.subMax.setValue(subMax);
        settings.mulMin.setValue(mulMin);
        settings.mulMax.setValue(mulMax);
        settings.divMin.setValue(divMin);
        settings.divMax.setValue(divMax);

        settings.subNeg.setValue(subNeg);
        settings.divDec.setValue(divDec);

        settings.factorCount.setValue(factorCount);
        settings.setLang(lang);

        return settings;
    }

}
