package exercise;

import com.jfoenix.controls.JFXTextField;

import exercise.calculator.Calculator;
import generators.DifficultyGenerator;
import generators.MarkGenerator;
import handlers.FileHandler;
import handlers.SoundHandler;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import resources.lang.Language;
import settings.Settings;
import statistics.Statistics;

import java.util.Set;

public class Tester {

    public static ExerciseSheet exercise;

    public static boolean isChecked = false;
    public static int exercises = 0;
    public static int correct = 0;
    public static long timePlayed = 0;
    public static double difficulty = DifficultyGenerator.getDifficulty();

    private Settings settings = Settings.getInstance();

    public void start(Scene scene) {
        generateExercise();
        setExercise(scene);

        String blacklist = "°!\"§$%&/()=?`´^<>;:_#'+*@€µ|~²³{[]}\\";

        TextField txt = (TextField) scene.lookup("#answerInput");
        txt.setOnKeyTyped(event -> {
            String input = event.getCharacter();
            char c = input.charAt(0);
            if (input.length() == 0 || Character.isAlphabetic(c) || blacklist.contains(input)) {
                event.consume();
                return;
            }
            String text = txt.getText();
            if ((text.contains("-") && c == '-') || ((text.contains(".") && (c == '.' || c == ',')))) {
                event.consume();
                return;
            }
            if (text.isEmpty() && (c == '.' || c == ',')) {
                event.consume();
                return;
            }
            if (c == ',') {
                txt.appendText(".");
                event.consume();
                return;
            }
        });
    }

    public void setExercise(Scene scene) {
        Label labelExercise = (Label) scene.lookup("#exercise");
        Label title = (Label) scene.lookup("#labelTitle");
        Label subtitle = (Label) scene.lookup("#labelSubheader");
        JFXTextField input = (JFXTextField) scene.lookup("#answerInput");
        labelExercise.setText(exercise.getExercise());
        labelExercise.setTextFill(Color.web("#292929"));
        title.setText(Language.get("test.title"));
        title.setTextFill(Color.web("#292929"));
        subtitle.setText(Language.get("test.subtitle"));
        input.setText("");
        isChecked = false;
        timePlayed = System.currentTimeMillis();
        ((Label) scene.lookup("#currentDifficulty")).setText(String.format(settings.lang.getValue(), "%.5f", difficulty));
    }

    public static void generateExercise() {
        Settings settings = Settings.getInstance();

        Calculator calc = Calculator.getRandomCalculator(settings.add.getValue(), settings.sub.getValue(), settings.mul.getValue(), settings.div.getValue(), settings.pow.getValue());
        int count = 2 + (int) (Math.random() * ((settings.factorCount.getValue() - 2) + 1));
        Exercise newExercise = new Exercise(calc, count);


        exercise = newExercise.generate();
    }

    public void check(Scene scene) throws Exception {
        timePlayed = System.currentTimeMillis() - timePlayed;

        String input = ((TextField) scene.lookup("#answerInput")).getText();
        input = input.replace(",", ".");

        if (input.isEmpty()) return;

        float answer = Float.valueOf(input);
        Label labelExercise = (Label) scene.lookup("#exercise");
        labelExercise.setText(exercise.getExerciseSolution());
        Label title = (Label) scene.lookup("#labelTitle");
        Label title2 = (Label) scene.lookup("#labelSubheader");

        exercises++;
        Statistics.setExercises(Statistics.getExercises() + 1);
        Statistics.addMillisecondsPlayed(timePlayed);

        SoundHandler sound = new SoundHandler(settings.sounds.getValue(), settings.volume);
        if (answer == exercise.getSolution()) {
            sound.playSound("/resources/sounds/correct.mp3");
            labelExercise.setTextFill(Color.web(settings.exerciseCorrect));
            title.setText(Language.get("test.title.correct"));
            title.setTextFill(Color.web(settings.exerciseCorrect));
            title2.setText(Language.get("test.subtitle.correct"));
            correct++;
            Statistics.setExercisesCorrect(Statistics.getExercisesCorrect() + 1);
            Statistics.addScore(difficulty);
        } else {
            sound.playSound("/resources/sounds/wrong.mp3");
            labelExercise.setTextFill(Color.web(settings.exerciseWrong));
            title.setText(Language.get("test.title.wrong"));
            title.setTextFill(Color.web(settings.exerciseWrong));
            title2.setText(Language.get("test.subtitle.wrong"));
        }
        FileHandler.writeStats();

        Label exercisesCorrect = (Label) scene.lookup("#exercisesCorrect");
        exercisesCorrect.setText(correct + " / " + exercises);
        Label mark = (Label) scene.lookup("#mark");
        double ecp = ((double) correct / (double) exercises) * (double) 15;
        String note = MarkGenerator.main(ecp);

        mark.setText(note);

        isChecked = true;
    }

    public void newExercise(Scene scene) {
        generateExercise();
        setExercise(scene);
    }

}
