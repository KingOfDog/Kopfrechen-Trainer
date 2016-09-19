package init;

import java.io.IOException;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import javafx.Switch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.lang.Language;

public class InitTester {

	private Stage stage;
	
	public InitTester(Stage stage) {
		this.stage = stage;
	}
	
	public Scene init() throws IOException {
		Switch sw = new Switch();
		Scene scene = sw.switchScene("/javafx/tester.fxml", this.stage);
		this.stage.setScene(scene);
		
		InitToolbar itb = new InitToolbar(scene);
		itb.init(Language.get("test.toolbar"), true, MaterialIcon.DONE, stage, "tester");
		
		return scene;
	}
	
}
