package main;

import handlers.FileHandler;
import handlers.UpdateHandler;
import initializers.InitMain;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import resources.lang.Language;
import settings.Settings;
import updates.Version;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public class Main extends Application {

	private Settings settings = Settings.getInstance();
	
	@Override
	public void start(Stage stage) throws Exception {
//		Initialize the window and set it's width, height, title and icon 
		stage.setMinHeight(500);
		stage.setMinWidth(600);
		stage.setHeight(settings.startHeight.getValue());
		stage.setWidth(settings.startWidth.getValue());
//		stage.setMaximized(settings.startMaximized.getValue());
		stage.getIcons().add(new Image("/resources/img/icon.png"));
		stage.setTitle("MATH - Math training game");
		
//		Initialize the main menu
		InitMain init = new InitMain(stage);
		init.init();
		
//		Search for updates and send notification if new version is available
		if(settings.automaticUpdates.getValue()) {
			UpdateHandler updateHandler = new UpdateHandler();
			Version version = updateHandler.update();
			boolean update = false;
			if(version.isUpdate()) {
				update = true;
			}
			
			if(update) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle(Language.get("update.available"));
				alert.setHeaderText(String.format(Language.get("update.available.header"), version.getVersion()));
				alert.setContentText(String.format(Language.get("update.available.content"), version.getVersion()));

				ButtonType download = new ButtonType(Language.get("update.yes"));
				ButtonType changelog = new ButtonType(Language.get("update.changes"));
				ButtonType cancel = new ButtonType(Language.get("update.no"), ButtonBar.ButtonData.CANCEL_CLOSE);

				alert.getButtonTypes().setAll(download, changelog, cancel);
				Optional<ButtonType> result = alert.showAndWait();
				if(result.get() == download) {
					updateHandler.getUpdate(version.getPath(), version.getCheckSum(), version.getVersion());
				} else if(result.get() == changelog) {
					if(Desktop.isDesktopSupported()) {
						Desktop.getDesktop().browse(new URI(version.getPath()));
					}
				}
			}
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
