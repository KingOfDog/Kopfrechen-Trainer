package initializers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToolbar;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.utils.MaterialIconFactory;
import exercise.Tester;
import handlers.SettingsHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import resources.lang.Language;

public class InitToolbar {

    private Scene scene;

    public InitToolbar(Scene scene) {
        this.scene = scene;
    }

    public Scene init(String title, boolean back, MaterialIcon iconRight, Stage stage, String str) {
        StackPane sp = (StackPane) scene.lookup("#container");

        JFXToolbar tb = new JFXToolbar();
        tb.setPrefHeight(50);
        tb.setMaxHeight(tb.getPrefHeight());

        StackPane spLeft = new StackPane();
        Label lbl = new Label(title);
        lbl.setTranslateX(30);
        spLeft.getChildren().add(lbl);
        if (back) {
            JFXButton btnBack = new JFXButton();
            MaterialIconFactory.get().setIcon(btnBack, MaterialIcon.ARROW_BACK, "2.5em");
            btnBack.setTooltip(new Tooltip(Language.get("toolbar.back")));
            spLeft.getChildren().add(btnBack);
            StackPane.setAlignment(btnBack, Pos.CENTER_LEFT);
            btnBack.setCancelButton(true);
            btnBack.setOnAction(event -> {
                try {
                    InitMain main = new InitMain(stage);
                    main.init();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        if (iconRight != null) {
            StackPane spRight = new StackPane();
            JFXButton btnRight = new JFXButton();
            btnRight.setDefaultButton(true);
            MaterialIconFactory.get().setIcon(btnRight, iconRight, "2.5em");
            spRight.getChildren().add(btnRight);
            tb.setRight(spRight);

            switch (str) {
                case "settings":
                    btnRight.setTooltip(new Tooltip(Language.get("settings.save")));
                    btnRight.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            SettingsHandler.updateAndSave(scene, sp, stage);
                        }

                    });
                    break;
                case "tester":
                    Tester aufgaben = new Tester();
                    aufgaben.start(scene);

                    btnRight.setTooltip(new Tooltip(Language.get("test.finish")));
                    btnRight.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            if (Tester.isChecked) {
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
                default:
                    break;
            }
        }

        tb.setLeft(spLeft);

        sp.getChildren().add(0, tb);
        StackPane.setAlignment(tb, Pos.TOP_CENTER);

        return this.scene;
    }

}
