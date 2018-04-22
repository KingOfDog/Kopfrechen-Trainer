package handlers;

import java.io.IOException;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;

//import com.sun.org.apache.xpath.internal.operations.Bool;
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
    private static Node exerciseContainer;
    private static Node mainContainer;
    private static Settings settings = Settings.getInstance();

    public static void setDefault(Scene scene) {
        SettingsHandler.scene = scene;
        SettingsHandler.exerciseContainer = ((ScrollPane) scene.lookup("#exerciseContainer")).getContent();
        SettingsHandler.mainContainer = ((ScrollPane) scene.lookup("#mainContainer")).getContent();

//		Replace the checkboxes' values with the values from the settings file
        for (BooleanProperty p : settings.booleanSettings) {
            try {
                ((JFXCheckBox) lookupProperty(p)).setSelected(p.getValue());
            } catch (NullPointerException e) {
                System.err.println("Could not find a Checkbox with the id:" + p.getName() + " Could not set the Checkbox to the value:" + p.getValue());
            } catch (ClassCastException e) {
                System.err.println("Could not cast the Node with the id:" + p.getName() + " to a Checkbox. The value:" + p.getValue() + " was not set!");
            }
        }

//		Replace the text fields' values with the values from the settings file
        for (IntegerProperty p : settings.integerSettings) {
            try {
                ((JFXTextField) lookupProperty(p)).setText(String.valueOf(p.getValue()));
            } catch (NullPointerException e) {
                System.err.println("Could not find a TextField with the id:" + p.getName() + ". The value:" + p.getValue() + " was not set!");
            } catch (ClassCastException e) {
                System.err.println("Could not cast the Node with the id:" + p.getName() + " to a TextField. The value:" + p.getValue() + " was not set!");
            }
        }

//		Replace the sliders' values with the values from the settings file
        for(IntegerProperty p : settings.sliderSettings) {
            try {
                ((JFXSlider) lookupProperty(p)).setValue(p.getValue());
            } catch (NullPointerException e) {
                System.err.println("Could not find a Slider with the id:" + p.getName() + ". The value:" + p.getValue() + " was not set!");
            } catch (ClassCastException e) {
                System.err.println("Could not cast the Node with the id:" + p.getName() + " to a Slider. The value:" + p.getValue() + " was not set!");
            }
        }

//		Label difficulty = (Label) scene.lookup("#difficulty");
//		difficulty.setText(String.valueOf(Difficulty.getDifficulty()));

        @SuppressWarnings("unchecked")
        JFXComboBox<Label> language = (JFXComboBox<Label>) mainContainer.lookup("#language");

//		Add all available languages the the combo box
        language.getItems().add(new Label(Locales.ENGLISH.getName()));
        language.getItems().add(new Label(Locales.GERMAN.getName()));
        language.getItems().add(new Label(Locales.FRENCH.getName()));
        language.getItems().add(new Label(Locales.CHINESE.getName()));
        language.getItems().add(new Label(Locales.RUSSIAN.getName()));
        language.getItems().add(new Label(Locales.SPAIN.getName()));

//		Select the current language
        if (settings.lang.getValue().equals(Locales.ENGLISH.getLocale())) language.getSelectionModel().select(0);
        else if (settings.lang.getValue().equals(Locales.GERMAN.getLocale())) language.getSelectionModel().select(1);
        else if (settings.lang.getValue().equals(Locales.FRENCH.getLocale())) language.getSelectionModel().select(2);
        else if (settings.lang.getValue().equals(Locales.CHINESE.getLocale())) language.getSelectionModel().select(3);
        else if (settings.lang.getValue().equals(Locales.RUSSIAN.getLocale())) language.getSelectionModel().select(4);
        else if (settings.lang.getValue().equals(Locales.SPAIN.getLocale())) language.getSelectionModel().select(5);
        else language.getSelectionModel().select(0);
    }

    @FXML
    public static void updateAndSave(Scene scene, StackPane sp, Stage stage) {
        update(scene);

        FileHandler.writeSettings();

        JFXSnackbar notify = new JFXSnackbar(sp);
        notify.show(Language.get("settings.saved"), 5000);

        SettingsHandler.setDefault(scene);

    }

    public static void update(Scene scene) {
        for(BooleanProperty p : settings.booleanSettings) {
            p.setValue(((JFXCheckBox) lookupProperty(p)).isSelected());
        }

        for(IntegerProperty p : settings.integerSettings) {
            p.setValue(Integer.parseInt(((JFXTextField) lookupProperty(p)).getText()));
        }

        for(IntegerProperty p : settings.sliderSettings) {
            p.setValue((int) ((JFXSlider) lookupProperty(p)).getValue());
        }

		@SuppressWarnings("unchecked")
		String language = ((Label) ((JFXComboBox<Label>) scene.lookup("#language")).getSelectionModel().getSelectedItem()).getText();
		if(language.equals(Locales.ENGLISH.getName())) {
			settings.lang.setValue(Locales.ENGLISH.getLocale());
		} else if(language.equals(Locales.GERMAN.getName())) {
			settings.lang.setValue(Locales.GERMAN.getLocale());
		} else if(language.equals(Locales.FRENCH.getName())) {
			settings.lang.setValue(Locales.FRENCH.getLocale());
		} else if(language.equals(Locales.CHINESE.getName())) {
			settings.lang.setValue(Locales.CHINESE.getLocale());
		} else if(language.equals(Locales.RUSSIAN.getName())) {
			settings.lang.setValue(Locales.RUSSIAN.getLocale());
		} else if(language.equals(Locales.SPAIN.getName())) {
			settings.lang.setValue(Locales.SPAIN.getLocale());
		} else {
			settings.lang.setValue(Locales.ENGLISH.getLocale());
		}
    }

    private static Node lookupProperty(Property prop) {
        Node n = scene.lookup("#" + prop.getName());
        if (n == null) {
            n = mainContainer.lookup("#" + prop.getName());
            if (n == null) {
                n = exerciseContainer.lookup("#" + prop.getName());
            }
        }
        return n;
    }
}
