package initializers;

import java.io.IOException;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import handlers.SceneHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.lang.Language;

public class InitTester {

	private Stage stage;
	
	public InitTester(Stage stage) {
		this.stage = stage;
	}
	
	public Scene init() throws IOException {
		SceneHandler sw = new SceneHandler();
		Scene scene = sw.switchScene("tester", this.stage);
		this.stage.setScene(scene);
		
		InitToolbar itb = new InitToolbar(scene);
		itb.init(Language.get("test.toolbar"), true, MaterialIcon.DONE, stage, "tester");

		return scene;
	}
	
}
