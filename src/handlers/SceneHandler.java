package handlers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import settings.Settings;

public class SceneHandler implements Initializable {

	private Settings settings = Settings.getInstance();
	
	public Scene switchScene(String name, Stage stage) throws IOException {
		Font.loadFont("/resources/fonts/Roboto-Bold.ttf", 16);
		Font.loadFont("/resources/fonts/Roboto-Medium.ttf", 16);
		Font.loadFont("/resources/fonts/Roboto-Light.ttf", 16);
		Font.loadFont("/resources/fonts/cc-icons.ttf", 16);
		FXMLLoader loader = new FXMLLoader();
		loader.setResources(ResourceBundle.getBundle("resources.lang.lang", settings.lang));
		Parent parent = loader.load(getClass().getResource(String.format("/resources/javafx/%s.fxml", name)).openStream());
		Scene scene = new Scene(parent);
		scene.getStylesheets().add(String.format("/resources/styles/%s.css", name));
		scene.getStylesheets().add("/resources/styles/toolbar.css");
		stage.setScene(scene);
		stage.show();
		
		return scene;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
}
