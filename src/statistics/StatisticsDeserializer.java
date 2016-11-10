package statistics;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class StatisticsDeserializer implements JsonDeserializer<Statistics> {

	@Override
	public Statistics deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
		final JsonObject jsonObject = json.getAsJsonObject();
		
		final int ex = jsonObject.get("exercises").getAsInt();
		final int exCor = jsonObject.get("exercisesCorrect").getAsInt();
		final long milPlay = jsonObject.get("millisecondsPlayed").getAsLong();
		final String session = jsonObject.get("session").getAsString();
		final double score = jsonObject.get("score").getAsDouble();
		
		final Statistics stats = new Statistics();
		Statistics.setExercises(ex);
		Statistics.setExercisesCorrect(exCor);
		Statistics.setMillisecondsPlayed(milPlay);
		Statistics.setSession(session);
		Statistics.setScore(score);
		
		return stats;
	}

}
