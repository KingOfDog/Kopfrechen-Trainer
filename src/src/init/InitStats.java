package init;

import java.io.IOException;
import javafx.Switch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Stats;
import resources.lang.Language;

public class InitStats {

	private Stage stage;
	
	public InitStats(Stage stage) {
		this.stage = stage;
	}
	
	public Scene init() throws IOException {
		Switch sw = new Switch();
		Scene scene = sw.switchScene("/javafx/stats.fxml", this.stage);
		Stats stats = new Stats();
		stats.start(scene);
		
		InitToolbar itb = new InitToolbar(scene);
		itb.init(Language.get("stats.toolbar"), true, null, stage, "stats");
		
		return scene;
	}
	
}
