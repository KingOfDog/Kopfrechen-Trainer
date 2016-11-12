package handlers;

import java.io.IOException;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;

import initializers.InitSettings;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import properties.BooleanProperty;
import properties.IntegerProperty;
import properties.Property;
import resources.lang.Locales;
import settings.Settings;
import resources.lang.Language;

public class SettingsHandler {

    private static Scene scene;
    private static Node scroll;
    private static Settings settings = Settings.getInstance();

    public static void setDefault(Scene scene) {
        SettingsHandler.scene = scene;
        SettingsHandler.scroll = ((ScrollPane) scene.lookup("#scrollContainer")).getContent();

//		Replace the checkboxes' values with the values from the settings file
        for (BooleanProperty p : settings.operationSettings) {
            ((JFXCheckBox) lookupProperty(p)).setSelected(p.getValue());
        }

        setCheckboxValue("windowMaximized", settings.startMaximized, false);
        setCheckboxValue("automaticUpdates", settings.automaticUpdates, false);

//		Replace the text fields' values with the values from the settings file
        for (IntegerProperty p : settings.minMaxSettings) {
            ((JFXTextField) lookupProperty(p)).setText(String.valueOf(p.getValue()));
        }

//		Replace the sliders' values with the values from the settings file
        setSliderValue("factorCount", settings.factorCount.getValue(), true);
        setSliderValue("windowWidth", settings.startWidth, false);
        setSliderValue("windowHeight", settings.startHeight, false);

//		Label difficulty = (Label) scene.lookup("#difficulty");
//		difficulty.setText(String.valueOf(Difficulty.getDifficulty()));

        @SuppressWarnings("unchecked")
        JFXComboBox<Label> language = (JFXComboBox<Label>) scene.lookup("#language");

//		Add all available languages the the combo box
        language.getItems().add(new Label(Locales.ENGLISH.getName()));
        language.getItems().add(new Label(Locales.GERMAN.getName()));
        language.getItems().add(new Label(Locales.FRENCH.getName()));
        language.getItems().add(new Label(Locales.CHINESE.getName()));
        language.getItems().add(new Label(Locales.RUSSIAN.getName()));
        language.getItems().add(new Label(Locales.SPAIN.getName()));

//		Select the current language
        if (settings.lang.equals(Locales.ENGLISH.getLocale())) language.getSelectionModel().select(0);
        else if (settings.lang.equals(Locales.GERMAN.getLocale())) language.getSelectionModel().select(1);
        else if (settings.lang.equals(Locales.FRENCH.getLocale())) language.getSelectionModel().select(2);
        else if (settings.lang.equals(Locales.CHINESE.getLocale())) language.getSelectionModel().select(3);
        else if (settings.lang.equals(Locales.RUSSIAN.getLocale())) language.getSelectionModel().select(4);
        else if (settings.lang.equals(Locales.SPAIN.getLocale())) language.getSelectionModel().select(5);
        else language.getSelectionModel().select(0);
    }

    @FXML
    public static void updateAndSave(Scene scene, StackPane sp, Stage stage) {
        update(scene);

        FileHandler.writeSettings();

        JFXSnackbar notify = new JFXSnackbar(sp);
        notify.show(Language.get("settings.saved"), 5000);

        InitSettings init = new InitSettings(stage);
        try {
            init.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update(Scene scene) {
//		Settings.add = getCheckboxValue("add");
//		Settings.sub = getCheckboxValue("sub");
//		Settings.mul = getCheckboxValue("mul");
//		Settings.div = getCheckboxValue("div");
//		Settings.subNeg = getCheckboxValue("subNeg");
//		Settings.divComma = getCheckboxValue("divDec");
//		Settings.startMaximized = getCheckboxValue("windowMaximized");
//		Settings.automaticUpdates = getCheckboxValue("automaticUpdates");
//
//		Settings.addMin = Integer.valueOf(getTextfieldValue("addMin"));
//		Settings.addMax = Integer.valueOf(getTextfieldValue("addMax"));
//		Settings.subMin = Integer.valueOf(getTextfieldValue("subMin"));
//		Settings.subMax = Integer.valueOf(getTextfieldValue("subMax"));
//		Settings.mulMin = Integer.valueOf(getTextfieldValue("mulMin"));
//		Settings.mulMax = Integer.valueOf(getTextfieldValue("mulMax"));
//		Settings.divMin = Integer.valueOf(getTextfieldValue("divMin"));
//		Settings.divMax = Integer.valueOf(getTextfieldValue("divMax"));
//
//		Settings.factorCount = (int) getSliderValue("factorCount");
//		Settings.startWidth = (int) getSliderValue("windowWidth");
//		Settings.startHeight = (int) getSliderValue("windowHeight");
//
//		@SuppressWarnings("unchecked")
//		String language = ((Label) ((JFXComboBox<Label>) scene.lookup("#language")).getSelectionModel().getSelectedItem()).getText();
//		if(language.equals(Locales.ENGLISH.getName())) {
//			Settings.lang = Locales.ENGLISH.getLocale();
//		} else if(language.equals(Locales.GERMAN.getName())) {
//			Settings.lang = Locales.GERMAN.getLocale();
//		} else if(language.equals(Locales.FRENCH.getName())) {
//			Settings.lang = Locales.FRENCH.getLocale();
//		} else if(language.equals(Locales.CHINESE.getName())) {
//			Settings.lang = Locales.CHINESE.getLocale();
//		} else if(language.equals(Locales.RUSSIAN.getName())) {
//			Settings.lang = Locales.RUSSIAN.getLocale();
//		} else if(language.equals(Locales.SPAIN.getName())) {
//			Settings.lang = Locales.SPAIN.getLocale();
//		} else {
//			Settings.lang = Locales.ENGLISH.getLocale();
//		}
    }

    private static Node lookupProperty(Property prop) {
        Node n = scene.lookup("#" + prop.getName());
        if (n == null) {
            n = scroll.lookup("#" + prop.getName());
        }
        return n;
    }

    private static boolean getCheckboxValue(String selector) {
        return ((JFXCheckBox) scene.lookup("#" + selector)).isSelected();
    }

    private static void setCheckboxValue(String selector, boolean value, boolean scrollPane) {
        if (scrollPane) ((JFXCheckBox) scroll.lookup("#" + selector)).setSelected(value);
        else ((JFXCheckBox) scene.lookup("#" + selector)).setSelected(value);
    }

    private static String getTextfieldValue(String selector) {
        return ((JFXTextField) scene.lookup("#" + selector)).getText();
    }

    private static void setTextfieldValue(String selector, int value, boolean scrollPane) {
        if (scrollPane) ((JFXTextField) scroll.lookup("#" + selector)).setText(String.valueOf(value));
        else ((JFXTextField) scene.lookup("#" + selector)).setText(String.valueOf(value));
    }

    private static double getSliderValue(String selector) {
        return ((JFXSlider) scene.lookup("#" + selector)).getValue();
    }

    private static void setSliderValue(String selector, double value, boolean scrollPane) {
        if (scrollPane) ((JFXSlider) scroll.lookup("#" + selector)).setValue(value);
        else ((JFXSlider) scene.lookup("#" + selector)).setValue(value);
    }
}
