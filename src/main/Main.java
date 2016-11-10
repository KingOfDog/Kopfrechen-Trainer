package main;

import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import handlers.FileHandler;
import initializers.InitMain;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import resources.lang.Language;
import settings.Settings;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
//		Initialize the window and set it's width, height, title and icon 
		stage.setMinHeight(450);
		stage.setMinWidth(700);
		stage.setHeight(Settings.startHeight);
		stage.setWidth(Settings.startWidth);
		stage.setMaximized(Settings.startMaximized);
		stage.getIcons().add(new Image("/resources/img/icon.png"));
		stage.setTitle("MATH - Math training game");
		
//		Initialize the main menu
		InitMain init = new InitMain(stage);
		init.init();
		
//		Search for updates and send notification if new version is available
		if(Settings.automaticUpdates) {
//			String[] version = UpdateHandler.update();
//			boolean update = false;
//			if(version[0] == "true") {
//				update = true;
//			}
			
//			if(update) {
//				Alert alert = new Alert(AlertType.INFORMATION);
//				alert.setTitle(Language.get("update.available"));
//				alert.setHeaderText(String.format(Language.get("update.available.header"), version[1]));
//				alert.setContentText(String.format(Language.get("update.available.content"), version[1]));
//
//				ButtonType download = new ButtonType(Language.get("update.yes"));
//				ButtonType changelog = new ButtonType(Language.get("update.changes"));
//				ButtonType cancel = new ButtonType(Language.get("update.no"), ButtonData.CANCEL_CLOSE);
//
//				alert.getButtonTypes().setAll(download, changelog, cancel);
//				Optional<ButtonType> result = alert.showAndWait();
//				if(result.get() == download) {
//					UpdateHandler.getUpdate(version[2], version[4], version[1]);
//				} else if(result.get() == changelog) {
//					if(Desktop.isDesktopSupported()) {
//						Desktop.getDesktop().browse(new URI(version[3]));
//					}
//				}
//			}
		}
	}
	
	public static void main(String[] args) throws Exception  {
		try {
//			Load the application's files and import the saved settings and statistics
			FileHandler.main();
			FileHandler.getSettings();
			FileHandler.getStats();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Start the GUI
		launch(args);
	}
	
}
