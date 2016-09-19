package main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class FXMLDocumentController implements Initializable {
	
	@FXML
	private void newDifficulty(ActionEvent event) {
		Scene scene = ((Node) event.getSource()).getScene();
		Settings.update(scene);
		Label label = (Label) scene.lookup("#difficulty");
		label.setText(String.valueOf(Difficulty.getDifficulty()));
	}
	
	public void initialize(URL url, ResourceBundle rb) {

	}
}
