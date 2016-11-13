package statistics;

import generators.MarkGenerator;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import resources.lang.Language;
import settings.Settings;

public class Stats {

	private Settings settings = Settings.getInstance();

	public void start(Scene scene) {

		Label exercisesLabel = (Label) scene.lookup("#exercises");
		Label exercisesSolvedLabel = (Label) scene.lookup("#exercisesSolved");
		Label markPointsLabel = (Label) scene.lookup("#mark");
		Label markWordsLabel = (Label) scene.lookup("#markWords");
		Label timeLabel = (Label) scene.lookup("#timePlayed");
		Label averageTimeLabel = (Label) scene.lookup("#averageTime");
		Label scoreLabel = (Label) scene.lookup("#score");
		Label averageDifficultyLabel = (Label) scene.lookup("#averageDifficulty");

		int ex = Statistics.getExercises();
		int exSo = Statistics.getExercisesCorrect();
		double markPoints = Double.valueOf(exSo) / Double.valueOf(ex) * Double.valueOf(15);
		String markWord = MarkGenerator.main(markPoints);
		long timePlayed = Statistics.getMillisecondsPlayed();
		long second = (timePlayed / 1000) % 60;
		long minute = (timePlayed / (1000 * 60)) % 60;
		long hour = (timePlayed / (1000 * 60 * 60)) % 24;
		String timePlayedStr = String.format(settings.lang.getValue(), "%02d %s %02d %s %02d %s", hour,
				Language.get("stats.hours"), minute, Language.get("stats.minutes"), second,
				Language.get("stats.seconds"));
		float averageTime = ((float) timePlayed / 1000) / (float) ex;
		double score = Statistics.getScore();
		double averageDifficulty = score / exSo;

		exercisesLabel.setText(String.format(settings.lang.getValue(), "%d", ex));
		exercisesSolvedLabel.setText(String.format(settings.lang.getValue(), "%d", exSo));
		markPointsLabel.setText(String.format(settings.lang.getValue(), "%.5f", markPoints));
		markWordsLabel.setText(markWord);
		timeLabel.setText(timePlayedStr);
		averageTimeLabel.setText(String.format(settings.lang.getValue(), "%.5f %s", averageTime, Language.get("stats.seconds")));
		scoreLabel.setText(String.format(settings.lang.getValue(), "%.5f", score));
		averageDifficultyLabel.setText(String.format(settings.lang.getValue(), "%.5f", averageDifficulty));
	}

}
