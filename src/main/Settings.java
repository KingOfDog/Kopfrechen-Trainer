package main;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

@SuppressWarnings("restriction")
public class Settings {
	
	public static void setDefault(Scene scene) {
		CheckBox add = (CheckBox) scene.lookup("#addition");
		CheckBox sub = (CheckBox) scene.lookup("#subtraktion");
		CheckBox mul = (CheckBox) scene.lookup("#multiplikation");
		CheckBox div = (CheckBox) scene.lookup("#division");
		
		TextField addMin = (TextField) scene.lookup("#addMin");
		TextField addMax = (TextField) scene.lookup("#addMax");
		TextField subMin = (TextField) scene.lookup("#subMin");
		TextField subMax = (TextField) scene.lookup("#subMax");
		TextField mulMin = (TextField) scene.lookup("#mulMin");
		TextField mulMax = (TextField) scene.lookup("#mulMax");
		TextField divMin = (TextField) scene.lookup("#divMin");
		TextField divMax = (TextField) scene.lookup("#divMax");
		
		CheckBox subNeg = (CheckBox) scene.lookup("#subNeg");
		CheckBox divComma = (CheckBox) scene.lookup("#divComma");
		
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
	}
	
	@FXML
	public static void update(Scene scene) {
		boolean add = ((CheckBox) scene.lookup("#addition")).isSelected();
		boolean sub = ((CheckBox) scene.lookup("#subtraktion")).isSelected();
		boolean mul = ((CheckBox) scene.lookup("#multiplikation")).isSelected();
		boolean div = ((CheckBox) scene.lookup("#division")).isSelected();
		
		int addMin = Integer.valueOf(((TextField) scene.lookup("#addMin")).getText());
		int addMax = Integer.valueOf(((TextField) scene.lookup("#addMax")).getText());
		int subMin = Integer.valueOf(((TextField) scene.lookup("#subMin")).getText());
		int subMax = Integer.valueOf(((TextField) scene.lookup("#subMax")).getText());
		int mulMin = Integer.valueOf(((TextField) scene.lookup("#mulMin")).getText());
		int mulMax = Integer.valueOf(((TextField) scene.lookup("#mulMax")).getText());
		int divMin = Integer.valueOf(((TextField) scene.lookup("#divMin")).getText());
		int divMax = Integer.valueOf(((TextField) scene.lookup("#divMax")).getText());
		
		boolean subNeg = ((CheckBox) scene.lookup("#subNeg")).isSelected();
		boolean divComma = ((CheckBox) scene.lookup("#divComma")).isSelected();
		
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
		
		SaveFiles.writeSettings();
	}
	
}
