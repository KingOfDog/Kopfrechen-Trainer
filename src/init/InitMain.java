package init;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import resources.lang.Language;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.utils.FontAwesomeIconFactory;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.utils.MaterialIconFactory;
import javafx.Switch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
		
		InitToolbar itb = new InitToolbar(scene);
		scene = itb.init(Language.get("home.toolbar"), false, null, stage, "main");
		
		JFXButton btnStart = new JFXButton();
		MaterialIconFactory.get().setIcon(btnStart, MaterialIcon.PLAY_CIRCLE_FILLED, "10em");
		btnStart.setDefaultButton(true);
		btnStart.getStyleClass().add("blue-button-icon");
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
		btnStart.setTooltip(new Tooltip(Language.get("home.hover.play")));
		sp.getChildren().add(btnStart);
		
		// Add settings button
		JFXButton btnSettings = new JFXButton();
		FontAwesomeIconFactory.get().setIcon(btnSettings, FontAwesomeIcon.COG, "3.5em");
		btnSettings.setCancelButton(true);
		btnSettings.getStyleClass().add("gray-button-icon");
		btnSettings.setTranslateX(150);
		btnSettings.setTooltip(new Tooltip(Language.get("home.hover.settings")));
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
		
		// Add statistics button
		JFXButton btnStats = new JFXButton();
		FontAwesomeIconFactory.get().setIcon(btnStats, FontAwesomeIcon.BAR_CHART, "3em");
		btnStats.getStyleClass().add("gray-button-icon");
		btnStats.setTranslateX(-150);
		btnStats.setTooltip(new Tooltip(Language.get("home.hover.stats")));
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
		
		btnStart.setTranslateY(15);
		btnStats.setTranslateY(15);
		btnSettings.setTranslateY(15);
		
		return scene;
	}
	
}
