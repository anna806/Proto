package grafika;

import proto.*;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * @author ltama
 *
 */
public class AszterG extends Pane{

	/**
	 * az AszterG-hez tatrtozó aszteroida
	 */
	Aszteroida a;
	
	/**
	 * A kéregvastagság leíró labelje
	 */
	Label rt1_a;
	/**
	 * A kéregvastagság adat labelje
	 */
   	Label rt1_b;
   	/**
	 * A Napközel leíró labelje
	 */
   	Label rt2_a;
   	/**
	 * A Napközel adat labelje
	 */
   	Label rt2_b;
   	Label rt3_a;
   	Label rt3_b;
   	
   	/**
	 * A telepesek leíró labelje
	 */
   	Label rb1_a;
   	/**
	 * A telepesek adattagjait tartalmazó tömb
	 */
   	ListView<String> rb1_b;
   	/**
	 * A Robot leíró labelje
	 */
   	Label rb2_a;
   	/**
	 * A robotok számát tartalmazó label
	 */
   	Label rb2_b;
   	/**
	 * Az Ufó leíró labelje
	 */
   	Label rb3_a;
   	/**
	 * Az Ufók számát tartalmazó label
	 */
   	Label rb3_b;
   	/**
	 * Az Kapuk leíró labelje
	 */
   	Label rb4_a;
   	/**
	 * Az Kapukk számát tartalmazó label
	 */
   	Label rb4_b;
   	
   	
   	/**
   	 * Az AszterG konstruktora, beállítja az AszterG-hez tatrtozó aszteroidát
   	 * @param _a : az AszterG-hez tatrtozó aszteroida
   	 */
   	AszterG(Aszteroida _a){
   		a= _a;
   	}
   	
   	/**
	 * Az AszterG aszteroidáján lévõ robotok száma
	 */
   	Robot r ;
   	/**
	 * Az AszterG aszteroidáján lévõ ufók száma
	 */
	Ufo u ;
	/**
	 * Az AszterG aszteroidáján lévõ kapuk száma
	 */
	Teleportkapu k;
   	
	/**
	 * Felépíti és visszaadja az AszterG-t
	 * @param color : az AszterG körvonalának színe
	 * @return az AszterG kinézete
	 */
	public VBox felepit( String color) {
		
		 
		VBox base = new VBox();		
		GridPane rootR = new GridPane();
		 
	    rootR.setPadding(new Insets(20));
	    rootR.setHgap(25);
	    rootR.setVgap(15);
	    rootR.setStyle("-fx-background-color: WHITE");
	    rootR.setStyle("-fx-border-color: " + color);
	 
	    rt1_a = new Label("Kéregvastagság");
	   	rt1_b = new Label("");
	   	rt2_a = new Label("Napközel");
	   	rt2_b = new Label("");
	   	rt3_a = new Label("");
	   	rt3_b = new Label("");
	   	
	   	rb1_a = new Label("Telepes");
	   	rb1_b = new ListView<String>();
	   	rb2_a = new Label("Robot");
	   	rb2_b = new Label("");
	   	rb3_a = new Label("Ufó");
	   	rb3_b = new Label("");
	   	rb4_a = new Label("Kapu");
	   	rb4_b = new Label("");
	   	
	   	rb1_b.setMaxSize(60, 60);
	 
	   	//fenti címek
	   	GridPane.setHalignment(rt1_a, HPos.LEFT);
	   	GridPane.setHalignment(rt2_a, HPos.LEFT);
	   	GridPane.setHalignment(rt3_a, HPos.LEFT);
	   	rootR.add(rt1_a, 0, 0, 3, 1);
	   	rootR.add(rt2_a, 0, 1, 3, 1);
	   	rootR.add(rt3_a, 0, 2, 3, 1);
	       
	   	//fenti értékek
	   	GridPane.setHalignment(rt1_b, HPos.LEFT);
	   	GridPane.setHalignment(rt2_b, HPos.LEFT);
	   	GridPane.setHalignment(rt3_b, HPos.LEFT);
	   	rootR.add(rt1_b, 3, 0);
	   	rootR.add(rt2_b, 3, 1);
	   	rootR.add(rt3_b, 3, 2);
	   	
	   	//lenti címek
	   	GridPane.setHalignment(rb1_a, HPos.CENTER);
	   	GridPane.setHalignment(rb2_a, HPos.CENTER);
	   	GridPane.setHalignment(rb3_a, HPos.CENTER);
	   	GridPane.setHalignment(rb4_a, HPos.CENTER);
	   	rootR.add(rb1_a, 0, 3); //1, 6);
	   	rootR.add(rb2_a, 1, 3);
	   	rootR.add(rb3_a, 2, 3);
	   	rootR.add(rb4_a, 3, 3);
       
	   	//lenti értékek
	   	if(a!=null)
	   	setLabelsRed(a);
	   	
		GridPane.setHalignment(rb1_b, HPos.CENTER);
	   	GridPane.setHalignment(rb2_b, HPos.CENTER);
	   	GridPane.setHalignment(rb3_b, HPos.CENTER);
	   	GridPane.setHalignment(rb4_b, HPos.CENTER);
	   	rootR.add(rb1_b, 0, 4, 1, 6);
	   	rootR.add(rb2_b, 1, 4);
	   	rootR.add(rb3_b, 2, 4);
	   	rootR.add(rb4_b, 3, 4);
	   	
	   	base.getChildren().addAll(rootR);
	   	base.setStyle("-fx-background-color: LIGHTGREY");
		return base;
		
		
	}
	
	/**
	 * Beállítja az AszterG Labeljeit, a megkapott aszteroidának megfelelõen
	 * @param a: a kapott aszteroida 
	 */
	public void setLabelsRed(Aszteroida a) {
		if(a != null) {
			rt1_b.setText(String.valueOf(a.getKopenyVastagsag()));
			if(a.getNapkozel() == "true") {
				rt2_b.setText("+");
			} else {
				rt2_b.setText("-");
			}
			
			int robot = 0;
			int ufo = 0;
			int kapu = 0;
			
			r = new Robot();
			u = new Ufo();
			k = new Teleportkapu();
		
			List<Entitas> entitas = new ArrayList<Entitas>();
			entitas.add(r);
			entitas.add(u);
			
			List<Szomszed> szomszedok = new ArrayList<Szomszed>();
			szomszedok.add(k);
			
			for(int i = 0; i<a.EntitasokSize(); i++) {
				if(a.getEntitasObj(i).Kompatibilis(entitas.get(0))) {
					robot++;
				}
				if(a.getEntitasObj(i).Kompatibilis(entitas.get(1))) {
					ufo++;
				}
			}
			for(int i = 0; i<a.SzomszedokSize(); i++) {
				if(a.getSzomszedObj(i).Kompatibilis(szomszedok.get(0))) {
					kapu++;
				}
			}			
			
			rb1_b.getItems().clear();
		   	for(String nev: a.getNevek()) {
		   		rb1_b.getItems().add(nev);
		   	}
		   	
		   	rb2_b.setText(String.valueOf(robot));
		   	rb3_b.setText(String.valueOf(ufo));
		   	rb4_b.setText(String.valueOf(kapu));
		}
	}	
	
	/**
	 * Visszaadja az AszterG-hez tartozó aszteroidát
	 * @return az aszteroida
	 */
	public Aszteroida getA() {
		return a;
	}
}

