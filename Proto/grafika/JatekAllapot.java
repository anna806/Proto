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
	 * Itt l�that� maga a p�lya
	 */
	Jatekter jatekter;
	/**
	 * A j�t�khoz tartoz� men�s�v, ahol a men� gomb, ment�s gomb �s az aszteroid�k adatlapjai tal�lhat�k
	 */
	Menusav menusav;
	/**
	 * A j�t�khoz tartoz� m�velets�v, amivel a telepesek akci�kat tudnak v�gezni
	 */
	Muveletsav muveletsav;
	
	/**
	 * Maga a j�t�k
	 */
	Jatek game;
	/**
	 * Egy random sz�m, a j�t�k gener�l�s�hoz sz�ks�ges
	 */
	Random rand = new Random();
	
	/**
	 * Az ablak, amiben j�tsz�dik a j�t�k
	 */
	Stage stage;
	
	/**
	 * A j�t�k�llapot konstruktora
	 * Param�terk�nt megkapja az aszteroid�k sz�m�t �s a telepesek neveit, majd
	 * ezeknek megfelel�en l�trehoz egy j�t�kot.
	 * @param asz : Az aszteroid�k sz�ma
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
	 * Fel�p�ti a J�t�k�llapotot �s belerakja a f�ablakba.
	 * �tadja a f�men�t is param�ter k�nt, hogy vissza tudjon l�pni a j�t�kb�l.
	 * @param primary : a f�ablak
	 * @param oldscene : a f�men�
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
	 * A j�t�kosok k�z�tti l�peget�st kezeli
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
