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
import proto.Jatek;

public class JatekAllapot extends Pane implements EventHandler<ActionEvent>{
	
	Jatekter jatekter;
	Menusav menusav;
	Muveletsav muveletsav;
	
	Jatek game;
	
	JatekAllapot(){
		game = new Jatek();
		jatekter = new Jatekter();
		menusav = new Menusav();
		muveletsav = new Muveletsav();
	}
	
	void felepit(Stage primary, Scene oldscene, int aszam, List<String> nevek) {
		VBox menusavBox = new VBox();
		HBox muveletsavBox = new HBox();
		menusavBox = menusav.felepit(nevek, game, oldscene, primary);
		muveletsavBox = muveletsav.felepit(game.GetOv(), jatekter); 
		
		//mentés?
		//scene váltás?
		
		BorderPane borderPane = new BorderPane();
//	    borderPane.setCenter(appContent);
	    borderPane.setRight(menusavBox);
	    borderPane.setBottom(muveletsavBox);
	    
	    Scene scene = new Scene(borderPane, 300, 300); //méret
	    primary.setScene(scene);
	    primary.show();
	}
	public void handle(ActionEvent event) {
		
	}
}
