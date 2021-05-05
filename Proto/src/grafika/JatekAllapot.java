package grafika;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JatekAllapot extends Pane implements EventHandler<ActionEvent>{
	
	Jatekter jatekter;
	Menusav menusav;
	Muveletsav muveletsav;
	
	Stage primaryStage;
	
	JatekAllapot(){
		jatekter = new Jatekter();
		menusav = new Menusav();
		muveletsav = new Muveletsav();
	}
	
	void felepit(Stage primary, Scene oldscene, int aszam, List<String> nevek) {
		VBox menusavBox = new VBox();
		HBox muveletsavBox = new HBox();
		menusavBox = menusav.felepit(nevek);
		muveletsavBox = muveletsav.felepit(ov, j); //játéktérbõl szedi ki
		
		//mentés?
		//scene váltás?
		
		BorderPane borderPane = new BorderPane();
	    borderPane.setCenter(appContent);
	    borderPane.setRight(menusavBox);
	    borderPane.setBottom(muveletsavBox);
	    
	    Scene scene = new Scene(borderPane, 300, 300); //méret
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	void handle(ActionEvent event) {
		
	}
}
