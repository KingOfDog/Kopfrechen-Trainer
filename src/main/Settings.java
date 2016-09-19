package main;

import java.io.IOException;
import java.util.Locale;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;

import init.InitSettings;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import resources.lang.Language;

public class Settings {
	
	public static void setDefault(Scene scene) {
		JFXCheckBox add = (JFXCheckBox) scene.lookup("#addition");
		JFXCheckBox sub = (JFXCheckBox) scene.lookup("#subtraction");
		JFXCheckBox mul = (JFXCheckBox) scene.lookup("#multiplication");
		JFXCheckBox div = (JFXCheckBox) scene.lookup("#division");
		
		JFXTextField addMin = (JFXTextField) scene.lookup("#addMin");
		JFXTextField addMax = (JFXTextField) scene.lookup("#addMax");
		JFXTextField subMin = (JFXTextField) scene.lookup("#subMin");
		JFXTextField subMax = (JFXTextField) scene.lookup("#subMax");
		JFXTextField mulMin = (JFXTextField) scene.lookup("#mulMin");
		JFXTextField mulMax = (JFXTextField) scene.lookup("#mulMax");
		JFXTextField divMin = (JFXTextField) scene.lookup("#divMin");
		JFXTextField divMax = (JFXTextField) scene.lookup("#divMax");
		
		JFXCheckBox subNeg = (JFXCheckBox) scene.lookup("#subNeg");
		JFXCheckBox divComma = (JFXCheckBox) scene.lookup("#divComma");
		
		@SuppressWarnings("unchecked")
		JFXComboBox<Label> language = (JFXComboBox<Label>) scene.lookup("#language");
		Label difficulty = (Label) scene.lookup("#difficulty");
		
		add.setSelected(jsonFiles.Settings.add);
		sub.setSelected(jsonFiles.Settings.sub);
		mul.setSelected(jsonFiles.Settings.mul);
		div.setSelected(jsonFiles.Settings.div);
		addMin.setText(String.valueOf(jsonFiles.Settings.addMin));
		addMax.setText(String.valueOf(jsonFiles.Settings.addMax));
		subMin.setText(String.valueOf(jsonFiles.Settings.subMin));
		subMax.setText(String.valueOf(jsonFiles.Settings.subMax));
		mulMin.setText(String.valueOf(jsonFiles.Settings.mulMin));
		mulMax.setText(String.valueOf(jsonFiles.Settings.mulMax));
		divMin.setText(String.valueOf(jsonFiles.Settings.divMin));
		divMax.setText(String.valueOf(jsonFiles.Settings.divMax));
		subNeg.setSelected(jsonFiles.Settings.subNeg);
		divComma.setSelected(jsonFiles.Settings.divComma);
		difficulty.setText(String.valueOf(Difficulty.getDifficulty()));
		language.getItems().add(new Label("Deutsch"));
		language.getItems().add(new Label("English"));
		language.getItems().add(new Label("Français"));
		if(jsonFiles.Settings.lang.equals(new Locale("de", "DE"))) language.getSelectionModel().select(0);
		else if(jsonFiles.Settings.lang.equals(new Locale("fr", "FR"))) language.getSelectionModel().select(2);
		else language.getSelectionModel().select(1);
	}
	
	@FXML
	public static void updateAndSave(Scene scene, StackPane sp, Stage stage) {	
		update(scene);
		
		SaveFiles.writeSettings();
		
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
		boolean add = ((JFXCheckBox) scene.lookup("#addition")).isSelected();
		boolean sub = ((JFXCheckBox) scene.lookup("#subtraction")).isSelected();
		boolean mul = ((JFXCheckBox) scene.lookup("#multiplication")).isSelected();
		boolean div = ((JFXCheckBox) scene.lookup("#division")).isSelected();
		
		int addMin = Integer.valueOf(((JFXTextField) scene.lookup("#addMin")).getText());
		int addMax = Integer.valueOf(((JFXTextField) scene.lookup("#addMax")).getText());
		int subMin = Integer.valueOf(((JFXTextField) scene.lookup("#subMin")).getText());
		int subMax = Integer.valueOf(((JFXTextField) scene.lookup("#subMax")).getText());
		int mulMin = Integer.valueOf(((JFXTextField) scene.lookup("#mulMin")).getText());
		int mulMax = Integer.valueOf(((JFXTextField) scene.lookup("#mulMax")).getText());
		int divMin = Integer.valueOf(((JFXTextField) scene.lookup("#divMin")).getText());
		int divMax = Integer.valueOf(((JFXTextField) scene.lookup("#divMax")).getText());
		
		boolean subNeg = ((JFXCheckBox) scene.lookup("#subNeg")).isSelected();
		boolean divComma = ((JFXCheckBox) scene.lookup("#divComma")).isSelected();
		
		@SuppressWarnings("unchecked")
		String language = ((Label) ((JFXComboBox<Label>) scene.lookup("#language")).getSelectionModel().getSelectedItem()).getText();
		Locale lang;
		if(language.equals("Deutsch")) {
			lang = new Locale("de", "DE");
		} else if(language.equals("Français")) {
			lang = new Locale("fr", "FR");
		} else {
			lang = new Locale("en", "US");
		}
		
		jsonFiles.Settings.add = add;
		jsonFiles.Settings.sub = sub;
		jsonFiles.Settings.mul = mul;
		jsonFiles.Settings.div = div;
		jsonFiles.Settings.addMin = addMin;
		jsonFiles.Settings.addMax = addMax;
		jsonFiles.Settings.subMin = subMin;
		jsonFiles.Settings.subMax = subMax;
		jsonFiles.Settings.mulMin = mulMin;
		jsonFiles.Settings.mulMax = mulMax;
		jsonFiles.Settings.divMin = divMin;
		jsonFiles.Settings.divMax = divMax;
		jsonFiles.Settings.subNeg = subNeg;
		jsonFiles.Settings.divComma = divComma;
		jsonFiles.Settings.lang = lang;
	}
	
}
