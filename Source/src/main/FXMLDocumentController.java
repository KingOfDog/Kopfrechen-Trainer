package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class FXMLDocumentController implements Initializable {
	
	@FXML
	private void startGame(ActionEvent event) throws IOException {
		Parent abfrager = FXMLLoader.load(getClass().getResource("/src/main/abfrage.fxml"));
		Scene abfragerScene = new Scene(abfrager);
		Stage abfragerStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		abfragerStage.setScene(abfragerScene);
		abfragerStage.show();
		
		Abfrager aufgaben = new Abfrager();
		aufgaben.start(abfragerScene);
	}
	
	@FXML
	private void submitAnswer(ActionEvent event) {
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
		Parent stats = FXMLLoader.load(getClass().getResource("/src/main/stats.fxml"));
		Scene statsScene = new Scene(stats);
		Stage statsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		statsStage.setScene(statsScene);
		statsStage.show();
		
		Stats statClass = new Stats();
		statClass.start(statsScene);
	}
	
	@FXML
	private void goToSettings(ActionEvent event) throws IOException {
		Parent settings = FXMLLoader.load(getClass().getResource("/src/main/settings.fxml"));
		Scene setScene = new Scene(settings);
		Stage setStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		setStage.setScene(setScene);
		setStage.show();
		
		Settings.setDefault(setScene);
	}
	
	@FXML
	private void saveSettings(ActionEvent event) throws IOException {
		Scene scene = ((Node) event.getSource()).getScene().getWindow().getScene();
		Settings.update(scene);
	}
	
	@FXML
	private void backHome(ActionEvent event) throws IOException {
		Parent home = FXMLLoader.load(getClass().getResource("/src/main/main.fxml"));
		Scene homeScene = new Scene(home);
		Stage homeStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		homeStage.setScene(homeScene);
		homeStage.show();
	}
	
	public void initialize(URL url, ResourceBundle rb) {
		
	}
}
