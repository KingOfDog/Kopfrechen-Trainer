package init;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import javafx.Switch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class InitMain {

	private Stage stage;
	
	public InitMain(Stage stage) throws IOException {
		this.stage = stage;
	}
	
	public Scene init() throws IOException, InvocationTargetException {
		Switch sw = new Switch();
		Scene scene = sw.switchScene("/javafx/main.fxml", this.stage);
		StackPane sp = (StackPane) scene.lookup("#container");
		
		JFXButton btnStart = (JFXButton) scene.lookup("#btnStart");
		btnStart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					InitTester tester = new InitTester(stage);
					tester.init();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		// Add settings button
		JFXButton btnSettings = new JFXButton();
		FontAwesomeIconFactory.get().setIcon(btnSettings, FontAwesomeIcon.COG, "2em");
		btnSettings.setTranslateX(-25);
		btnSettings.setTranslateY(-15);
		btnSettings.setTooltip(new Tooltip(ResourceBundle.getBundle("resources.lang.lang", jsonFiles.Settings.lang).getString("settings_hover")));
		btnSettings.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				try {
					InitSettings settings = new InitSettings(stage);
					settings.init();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		sp.getChildren().add(btnSettings);
		StackPane.setAlignment(btnSettings, Pos.BOTTOM_RIGHT);
		
		// Add statistics button
		JFXButton btnStats = new JFXButton();
		FontAwesomeIconFactory.get().setIcon(btnStats, FontAwesomeIcon.BAR_CHART, "2em");
		btnStats.setTranslateX(25);
		btnStats.setTranslateY(-15);
		btnStats.setTooltip(new Tooltip(ResourceBundle.getBundle("resources.lang.lang", jsonFiles.Settings.lang).getString("statistics_hover")));
		btnStats.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						InitStats stats = new InitStats(stage);
						try {
							stats.init();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
		});
		sp.getChildren().add(btnStats);
		StackPane.setAlignment(btnStats, Pos.BOTTOM_LEFT);
		
		return scene;
	}
	
}
