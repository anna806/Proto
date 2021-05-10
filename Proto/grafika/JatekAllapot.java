package grafika;

import java.util.List;
import java.util.Random;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import proto.Jatek;
import proto.Main;
import proto.Telepes;

public class JatekAllapot extends Pane {
	
	/**
	 * Itt látható maga a pálya
	 */
	Jatekter jatekter;
	/**
	 * A játékhoz tartozó menüsáv, ahol a menü gomb, mentés gomb és az aszteroidák adatlapjai találhatók
	 */
	Menusav menusav;
	/**
	 * A játékhoz tartozó mûveletsáv, amivel a telepesek akciókat tudnak végezni
	 */
	Muveletsav muveletsav;
	
	/**
	 * Maga a játék
	 */
	Jatek game;
	/**
	 * Egy random szám, a játék generálásához szükséges
	 */
	Random rand = new Random();
	
	/**
	 * Az ablak, amiben játszódik a játék
	 */
	Stage stage;
	
	/**
	 * A játékállapot konstruktora
	 * Paraméterként megkapja az aszteroidák számát és a telepesek neveit, majd
	 * ezeknek megfelelõen létrehoz egy játékot.
	 * @param asz : Az aszteroidák száma
	 * @param nev : A telepeseknevei
	 */
	JatekAllapot(List<Integer> asz, List<String> nev){
		jatekter = new Jatekter();
		menusav = new Menusav();
		muveletsav = new Muveletsav();
		
		if(asz != null && nev != null) {
			Main.game.Start(asz.get(0));
		
			Telepes t = null;
			for(int i = 0; i<nev.size(); i++) {
				t = new Telepes();
				t.SetNev(nev.get(i));
				int s = Main.game.GetOv().getAszteroidak().size();
				t.SetAszteroida(Main.game.GetOv().GetAszteroida(rand.nextInt(s)));
				t.getAszteroida().Befogad(t);
				Main.game.GetOv().addTelepes(t);
				
			}
			Main.game.GetOv().setAktual(t);
		}
		Main.game.Kor();	
	}
	
	/**
	 * Felépíti a Játékállapotot és belerakja a fõablakba.
	 * Átadja a fõmenüt is paraméter ként, hogy vissza tudjon lépni a játékból.
	 * @param primary : a fõablak
	 * @param oldscene : a fõmenü
	 */
	void felepit(Stage primary, Scene oldscene) {
		stage = primary;
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
		muveletsavBox.setMinSize(610, 50);
		jatekter.getAszteroidaView(Main.game.GetOv().getAktual().getAszteroida()).setAktual(true);
		for(SzomszedView szv : jatekter.getAszteroidaView(Main.game.GetOv().getAktual().getAszteroida()).szomszedok) {
			szv.SzomszedMutat(jatekter.getAszteroidaView(Main.game.GetOv().getAktual().getAszteroida()));
		}
		p.setMinSize(820, 620);
		
		BorderPane borderPane = new BorderPane();
	    borderPane.setCenter(jatekterBox);
	    borderPane.setRight(menusavBox);
	    borderPane.setBottom(muveletsavBox);
	    
	    Scene scene = new Scene(borderPane, 1100, 700);
	    scene.setFill(Color.MIDNIGHTBLUE);
	    primary.setScene(scene);
	    primary.show();
	}
	
	/**
	 * A játékosok közötti lépegetést kezeli
	 */
	void ujJatekosJon() {
		if(Main.game.GetOv().GetTelepesekSize() == 0) {
			String s = Main.game.Vege(true);
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText(s);
			a.show();
			stage.close();
			Platform.exit();
		    System.exit(0);
		}
		Main.game.Kor();
	}
}
