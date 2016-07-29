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
import main.Abfrager;

public class InitTester {

	private Stage stage;
	
	public InitTester(Stage stage) {
		this.stage = stage;
	}
	
	public Scene init() throws IOException {
		Switch sw = new Switch();
		Scene scene = sw.switchScene("/javafx/tester.fxml", this.stage);
		StackPane sp = (StackPane) scene.lookup("#container");
		this.stage.setScene(scene);
		
//		JFXTextField input = (JFXTextField) scene.lookup("#answerInput");
		// TODO: add number and require validator
//		NumberValidator nv = new NumberValidator();
//		nv.setMessage("Zahl erforderlich");
//		nv.setIcon(new FontAwesomeIconView(FontAwesomeIcon.WARNING));
//		input.setValidators(nv);
		
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
		
		Abfrager aufgaben = new Abfrager();
		aufgaben.start(scene);
		
		JFXButton btnFinished = (JFXButton) scene.lookup("#finished");
		btnFinished.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(Abfrager.isChecked) {
					aufgaben.newExercise(scene);
				} else {
					try {
						aufgaben.check(scene);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		return scene;
	}
	
}
