package javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jsonFiles.Settings;

public class Switch implements Initializable {
	
	public Scene switchScene(String resource, Stage stage) throws IOException {
		Font.loadFont("/resources/fonts/Roboto-Bold.ttf", 16);
		Font.loadFont("/resources/fonts/Roboto-Medium.ttf", 16);
		Font.loadFont("/resources/fonts/Roboto-Light.ttf", 16);
		FXMLLoader loader = new FXMLLoader();
		loader.setResources(ResourceBundle.getBundle("resources.lang.lang", Settings.lang));
		Parent parent = loader.load(getClass().getResource(resource).openStream());
		Scene scene = new Scene(parent);
		scene.getStylesheets().add("/javafx/style.css");
		stage.setScene(scene);
		stage.show();
		
		return scene;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
}
