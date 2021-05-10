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

public class AszterG extends Pane{

	Aszteroida a;
	
	Label rt1_a;
   	Label rt1_b;
   	Label rt2_a;
   	Label rt2_b;
   	Label rt3_a;
   	Label rt3_b;
   	
   	Label rb1_a;
   	ListView<String> rb1_b;
   	Label rb2_a;
   	Label rb2_b;
   	Label rb3_a;
   	Label rb3_b;
   	Label rb4_a;
   	Label rb4_b;
   	
   	AszterG(Aszteroida _a){
   		a= _a;
   	}
   	
   	Robot r ;
	Ufo u ;
	Teleportkapu k;
   	
	public VBox felepit( String color) {
		
		 
		VBox base = new VBox();		
		GridPane rootR = new GridPane();
		 
	    rootR.setPadding(new Insets(20));
	    rootR.setHgap(25);
	    rootR.setVgap(15);
	    rootR.setStyle("-fx-background-color: WHITE");
	    rootR.setStyle("-fx-border-color: " + color);
	 
	    rt1_a = new Label("K�regvastags�g");
	   	rt1_b = new Label("");
	   	rt2_a = new Label("Napk�zel");
	   	rt2_b = new Label("");
	   	rt3_a = new Label("");
	   	rt3_b = new Label("");
	   	
	   	rb1_a = new Label("Telepes");
	   	rb1_b = new ListView<String>();
	   	rb2_a = new Label("Robot");
	   	rb2_b = new Label("");
	   	rb3_a = new Label("Uf�");
	   	rb3_b = new Label("");
	   	rb4_a = new Label("Kapu");
	   	rb4_b = new Label("");
	   	
	   	rb1_b.setMaxSize(60, 60);
	 
	   	//fenti c�mek
	   	GridPane.setHalignment(rt1_a, HPos.LEFT);
	   	GridPane.setHalignment(rt2_a, HPos.LEFT);
	   	GridPane.setHalignment(rt3_a, HPos.LEFT);
	   	rootR.add(rt1_a, 0, 0, 3, 1);
	   	rootR.add(rt2_a, 0, 1, 3, 1);
	   	rootR.add(rt3_a, 0, 2, 3, 1);
	       
	   	//fenti �rt�kek
	   	GridPane.setHalignment(rt1_b, HPos.LEFT);
	   	GridPane.setHalignment(rt2_b, HPos.LEFT);
	   	GridPane.setHalignment(rt3_b, HPos.LEFT);
	   	rootR.add(rt1_b, 3, 0);
	   	rootR.add(rt2_b, 3, 1);
	   	rootR.add(rt3_b, 3, 2);
	   	
	   	//lenti c�mek
	   	GridPane.setHalignment(rb1_a, HPos.CENTER);
	   	GridPane.setHalignment(rb2_a, HPos.CENTER);
	   	GridPane.setHalignment(rb3_a, HPos.CENTER);
	   	GridPane.setHalignment(rb4_a, HPos.CENTER);
	   	rootR.add(rb1_a, 0, 3); //1, 6);
	   	rootR.add(rb2_a, 1, 3);
	   	rootR.add(rb3_a, 2, 3);
	   	rootR.add(rb4_a, 3, 3);
       
	   	//lenti �rt�kek
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
	
	public Aszteroida getA() {
		return a;
	}
}

