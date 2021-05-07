package grafika;

import java.util.List;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proto.Jatek;
import proto.Telepes;

public class JatekAllapot extends Pane implements EventHandler<ActionEvent>{
	
	Jatekter jatekter;
	Menusav menusav;
	Muveletsav muveletsav;
	
	Jatek game;
	Random rand;
	
	JatekAllapot(List<Integer> asz, List<String> nev){
		game = new Jatek();
		jatekter = new Jatekter();
		menusav = new Menusav();
		muveletsav = new Muveletsav();
		
		game.Start(asz.get(0)); 
		Telepes t = null;
		for(int i = 0; i<nev.size(); i++) {
			t = new Telepes();
			t.SetNev(nev.get(i));
			int s = game.GetOv().getAszteroidak().size();
			t.SetAszteroida(game.GetOv().GetAszteroida(rand.nextInt(s)));
			game.GetOv().addTelepes(t);
		}
		game.GetOv().setAktual(t);
		
	}
	//jatekteret aszammal
	void felepit(Stage primary, Scene oldscene, List<Integer> aszam, List<String> nevek) {
		VBox menusavBox = new VBox();
		HBox muveletsavBox = new HBox();
		Group jG = new Group();
		HBox jatekterBox = new HBox();
		menusavBox = menusav.felepit(nevek, game, oldscene, primary, jatekter);
		muveletsavBox = muveletsav.felepit(game.GetOv(), jatekter); 
		jG = jatekter.felepit(game, oldscene, primary);
		jatekterBox.getChildren().addAll(jG);
		
		BorderPane borderPane = new BorderPane();
	    borderPane.setCenter(jatekterBox);
	    borderPane.setRight(menusavBox);
	    borderPane.setBottom(muveletsavBox);
	    
	    Scene scene = new Scene(borderPane, 800, 600); //méret
	    primary.setScene(scene);
	    primary.show();
	}
	public void handle(ActionEvent event) {
		
	}
}
