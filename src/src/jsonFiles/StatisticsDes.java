package jsonFiles;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class StatisticsDes implements JsonDeserializer<Statistics> {

	@Override
	public Statistics deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		
		final int ex = jsonObject.get("exercises").getAsInt();
		final int exCor = jsonObject.get("exercisesCorrect").getAsInt();
		
		final Statistics stats = new Statistics();
		stats.setExercises(ex);
		stats.setExercisesCorrect(exCor);
		
		return stats;
	}

}
