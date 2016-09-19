package init;

import java.io.IOException;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import javafx.Switch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Settings;
import resources.lang.Language;

public class InitSettings {

	private Stage stage;
	
	public InitSettings(Stage stage) {
		this.stage = stage;
	}
	
	public Scene init() throws IOException {
		Switch sw = new Switch();
		Scene scene = sw.switchScene("/javafx/settings.fxml", this.stage);
		this.stage.setScene(scene);
		
		Settings.setDefault(scene);
		
		InitToolbar itb = new InitToolbar(scene);
		itb.init(Language.get("settings.toolbar"), true, MaterialIcon.DONE, stage, "settings");
		
		return scene;
	}
	
}
