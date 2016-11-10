package handlers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;

import generators.DifficultyGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class FXMLHandler implements Initializable {
	
	@FXML
	private void newDifficulty(ActionEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		SettingsHandler.update(scene);
		Label label = (Label) scene.lookup("#difficulty");
		label.setText(String.valueOf(DifficultyGenerator.getDifficulty()));
	}
	
	@FXML
	private void widthChangeField(ActionEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		int width = Integer.valueOf(((JFXTextField) event.getSource()).getText());
		((JFXSlider) scene.lookup("#windowWidth")).setValue(width);
	}
	
	public void initialize(URL url, ResourceBundle rb) {

	}
}
