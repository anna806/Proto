package grafika;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class JatekAllapot extends Pane implements EventHandler<ActionEvent>{
	void felepit(Stage primary, Scene oldscene, int aszam, List<String> nevek) {
		Jatekter jatekter = new Jatekter(primary, oldscene, aszam, nevek);
		Menusav menusav = new Menusav(primary, oldscene, nevek);
		Muveletsav muveletsav = new Muveletsav(primary, oldscene, aszam, nevek);
	}
	void handle(ActionEvent event) {
		
	}
}
