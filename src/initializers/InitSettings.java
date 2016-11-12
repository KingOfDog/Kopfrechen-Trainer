package initializers;

import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import handlers.SceneHandler;
import handlers.SettingsHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.lang.Language;

import java.io.IOException;

public class InitSettings {

	private Stage stage;
	
	public InitSettings(Stage stage) {
		this.stage = stage;
	}
	
	public Scene init() throws IOException {
		SceneHandler sw = new SceneHandler();
		Scene scene = sw.switchScene("settings", this.stage);
		this.stage.setScene(scene);

		SettingsHandler.setDefault(scene);

		InitToolbar itb = new InitToolbar(scene);
		itb.init(Language.get("settings.toolbar"), true, MaterialIcon.DONE, stage, "settings");

		return scene;
	}
	
}
