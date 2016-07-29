package init;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import javafx.Switch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.Stats;

public class InitStats {

	private Stage stage;
	
	public InitStats(Stage stage) {
		this.stage = stage;
	}
	
	public Scene init() throws IOException {
		Switch sw = new Switch();
		Scene scene = sw.switchScene("/javafx/stats.fxml", this.stage);
		StackPane sp = (StackPane) scene.lookup("#container");
		Stats stats = new Stats();
		stats.start(scene);
		
		JFXButton btnBack = new JFXButton();
		btnBack.setTranslateX(10);
		btnBack.setTranslateY(10);
		btnBack.getStyleClass().add("btn-back");
		FontAwesomeIconFactory.get().setIcon(btnBack, FontAwesomeIcon.ARROW_LEFT, "2em");
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					InitMain main = new InitMain(stage);
					main.init();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		sp.getChildren().add(btnBack);
		StackPane.setAlignment(btnBack, Pos.TOP_LEFT);
		
		return scene;
	}
	
}
