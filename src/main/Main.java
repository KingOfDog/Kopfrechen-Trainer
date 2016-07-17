package main;

import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jsonFiles.Settings;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		ResourceBundle rb = ResourceBundle.getBundle("lang.lang", Settings.lang);
		loader.setResources(rb);
		Parent root = loader.load(getClass().getResource("/javafx/main.fxml").openStream());
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.getIcons().add(new Image("/main/iconWindow.png"));
		stage.setTitle(rb.getString("title"));
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
			alert.setHeaderText("Es ist ein Update auf Version " + version[1] + " verfügbar");
			alert.setContentText("Möchtest du das Update auf Version " + version[1] + " jetzt herunterladen?");
			
			ButtonType download = new ButtonType("Ja");
			ButtonType changelog = new ButtonType("Was ist neu?");
			ButtonType cancel = new ButtonType("Nein", ButtonData.CANCEL_CLOSE);
			
			alert.getButtonTypes().setAll(download, changelog, cancel);
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == download) {
				VersionCheck.getUpdate(version[2], version[4], version[1]);
			} else if(result.get() == changelog) {
				if(Desktop.isDesktopSupported()) {
					Desktop.getDesktop().browse(new URI(version[3]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception  {
		try {
			SaveFiles.main();
			SaveFiles.getSettings();
			SaveFiles.getStats();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		launch(args);
	}
	
}
