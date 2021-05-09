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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import proto.Jatek;
import proto.Main;
import proto.Telepes;

public class JatekAllapot extends Pane implements EventHandler<ActionEvent>{
	
	Jatekter jatekter;
	Menusav menusav;
	Muveletsav muveletsav;
	
	Jatek game;
	Random rand = new Random();
	
	JatekAllapot(List<Integer> asz, List<String> nev){
		//game = new Jatek();
		jatekter = new Jatekter();
		menusav = new Menusav();
		muveletsav = new Muveletsav();
		
		Main.game.Start(asz.get(0));
		
		Telepes t = null;
		for(int i = 0; i<nev.size(); i++) {
			t = new Telepes();
			t.SetNev(nev.get(i));
			int s = Main.game.GetOv().getAszteroidak().size();
			t.SetAszteroida(Main.game.GetOv().GetAszteroida(rand.nextInt(s)));
			Main.game.GetOv().addTelepes(t);
			
		}
		 
		Main.game.GetOv().setAktual(t);
		Main.game.Kor();
		

			
	}
	//jatekteret aszammal
	void felepit(Stage primary, Scene oldscene, List<Integer> aszam, List<String> nevek) {
		VBox menusavBox = new VBox();
		HBox muveletsavBox = new HBox();
		Group jG = new Group();
		Pane p = new Pane();
		HBox jatekterBox = new HBox();
		menusavBox = menusav.felepit( Main.game, oldscene, primary, jatekter);
		muveletsavBox = muveletsav.felepit(Main.game.GetOv(), jatekter, menusav, this); 
		p = jatekter.felepit(Main.game, oldscene, primary, menusav);
		jatekterBox.getChildren().addAll(p);
		
		menusavBox.setMaxSize(280, 650);
//		menusavBox.setMaxSize(450, 300);
		muveletsavBox.setMinSize(810, 70);
		p.setMinSize(620, 600);
		AszteroidaView akt= jatekter.getAszteroidaView(Main.game.GetOv().getAktual().getAszteroida());
		akt.setAktual(true);
		for(SzomszedView szv : akt.szomszedok) {
			szv.SzomszedMutat(akt);
		}
		p.setMinSize(620, 650);
		
		BorderPane borderPane = new BorderPane();
	    borderPane.setCenter(jatekterBox);
	    borderPane.setRight(menusavBox);
	    borderPane.setBottom(muveletsavBox);
	    
	    Scene scene = new Scene(borderPane, 900, 700); //méret
	    scene.setFill(Color.MIDNIGHTBLUE);
	    primary.setScene(scene);
	    primary.show();
	}
	public void handle(ActionEvent event) {
		
	}
	
	void ujJatekosJon() {
		Main.game.Kor();
		AszteroidaView red = jatekter.getAView(Main.game.GetOv().getAktual().getAszteroida());
		menusav.Update(red, false);
		System.out.println("Uj jon");
	}
}
