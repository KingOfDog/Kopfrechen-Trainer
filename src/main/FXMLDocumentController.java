package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.Switch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
	
	@FXML
	private void startGame(ActionEvent event) throws IOException {
		Switch sw = new Switch();
		Scene abfragerScene = sw.switchScene("/javafx/abfrage.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
		
		Abfrager aufgaben = new Abfrager();
		aufgaben.start(abfragerScene);
	}
	
	@FXML
	private void submitAnswer(ActionEvent event) throws Exception {
		Scene scene = ((Node) event.getSource()).getScene().getWindow().getScene();
		Abfrager solution = new Abfrager();
		if(Abfrager.isChecked) {
			solution.newExercise(scene);
		} else {
			solution.check(scene);
		}
	}
	
	@FXML
	private void statsShow(ActionEvent event) throws IOException {
		Switch sw = new Switch();
		Scene statsScene = sw.switchScene("/javafx/stats.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
		
		Stats statClass = new Stats();
		statClass.start(statsScene);
	}
	
	@FXML
	private void goToSettings(ActionEvent event) throws IOException {
		Switch sw = new Switch();
		Scene setScene = sw.switchScene("/javafx/settings.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
		
		Settings.setDefault(setScene);
	}
	
	@FXML
	private void newDifficulty(ActionEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Settings.update(scene);
		Label label = (Label) scene.lookup("#difficulty");
		label.setText(String.valueOf(Difficulty.getDifficulty()));
	}
	
	@FXML
	private void saveSettings(ActionEvent event) throws IOException {
		Scene scene = ((Node) event.getSource()).getScene().getWindow().getScene();
		Settings.update(scene);
	}
	
	@FXML
	private void backHome(ActionEvent event) throws IOException {
		Switch sw = new Switch();
		sw.switchScene("/javafx/main.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());
	}
	
	public void initialize(URL url, ResourceBundle rb) {

	}
}
