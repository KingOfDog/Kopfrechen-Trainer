package initializers;

import java.io.IOException;

import handlers.SceneHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.lang.Language;
import statistics.Stats;

public class InitStats {

	private Stage stage;
	
	public InitStats(Stage stage) {
		this.stage = stage;
	}
	
	public Scene init() throws IOException {
		SceneHandler sw = new SceneHandler();
		Scene scene = sw.switchScene("stats", this.stage);
		Stats stats = new Stats();
		stats.start(scene);
		
		InitToolbar itb = new InitToolbar(scene);
		itb.init(Language.get("stats.toolbar"), true, null, stage, "stats");

		return scene;
	}
	
}
