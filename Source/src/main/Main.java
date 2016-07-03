package main;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().add(new Image("main/iconWindow.png"));
		stage.setTitle("Kopfrechen-Trainer");
		stage.setMinHeight(450);
		stage.setMinWidth(650);
		stage.show();
		
		String[] version = VersionCheck.update();
		boolean update = false;
		if(version[0] == "true") {
			update = true;
		}
		
		if(update) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Update verfügbar");
			alert.setHeaderText("Es ist ein Update verfügbar");
			alert.setContentText("Möchtest du das Update jetzt herunterladen?");
			
			ButtonType download = new ButtonType("Ja");
			ButtonType changelog = new ButtonType("Was ist neu?");
			ButtonType cancel = new ButtonType("Nein", ButtonData.CANCEL_CLOSE);
			
			alert.getButtonTypes().setAll(download, changelog, cancel);
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == download) {
				
			} else if(result.get() == changelog) {
				if(Desktop.isDesktopSupported()) {
					Desktop.getDesktop().browse(new URI(version[3]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		try {
			SaveFiles.main();
		} catch (IOException e) {
			e.printStackTrace();
		}
		launch(args);
	}
	
}
