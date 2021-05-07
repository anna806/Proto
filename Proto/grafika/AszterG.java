package grafika;

import proto.*;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
   		a = _a;
   	}
   	
	public VBox felepit(List<String> nevek) {
		
		VBox base = new VBox();		
		GridPane rootR = new GridPane();
		 
	    rootR.setPadding(new Insets(20));
	    rootR.setHgap(25);
	    rootR.setVgap(15);
	    rootR.setStyle("-fx-background-color: WHITE");
	    rootR.setStyle("-fx-border-color: RED");
	 
	    rt1_a = new Label("Kéregvastagság");
	   	rt1_b = new Label("");
	   	rt2_a = new Label("Napközel");
	   	rt2_b = new Label("");
	   	rt3_a = new Label("Napvihar");
	   	rt3_b = new Label("");
	   	
	   	rb1_a = new Label("Telepes");
	   	rb1_b = new ListView<String>();
	   	rb2_a = new Label("Robot");
	   	rb2_b = new Label("");
	   	rb3_a = new Label("Ufó");
	   	rb3_b = new Label("");
	   	rb4_a = new Label("Kapu");
	   	rb4_b = new Label("");
	 
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
	   	rootR.add(rb1_a, 0, 3, 1, 6);
	   	rootR.add(rb2_a, 1, 3);
	   	rootR.add(rb3_a, 2, 3);
	   	rootR.add(rb3_a, 3, 3);
       
	   	//lenti értékek
	   	setLabelsRed(a, nevek);
	   	
	   	base.getChildren().addAll(rootR);	   	
		return base;
		
		
	}

	public void handle(ActionEvent event) {
		
	}
	
	
	
	public void setLabelsRed(Aszteroida a, List<String> nevek) {
		rt1_b.setText(String.valueOf(a.getKopenyVastagsag()));
		if(a.getNapkozel() == "true") {
			rt2_b.setText("+");
		} else {
			rt2_b.setText("-");
		}
		
		int robot = 0;
		int ufo = 0;
		int kapu = 0;
		//equals instenceof
		for(int i = 0; i<a.EntitasokSize(); i++) {
			String ent = a.getEntitas(i);
			if(ent.startsWith("r")) {
				robot++;
			}
			if(ent.startsWith("u")) {
				ufo++;
			}
		}
		for(int i = 0; i<a.SzomszedokSize(); i++) {
			String szom = a.getEntitas(i);
			if(szom.startsWith("k")) {
				kapu++;
			}
		}
		
		for(int i = 0; i< a.EntitasokSize(); i++) {
			
		}
		
	   	for(String nev: nevek) {
	   		rb1_b.getItems().add(nev);
	   	}
	   	rb2_b.setText(String.valueOf(robot));
	   	rb3_b.setText(String.valueOf(ufo));
	   	rb4_b.setText(String.valueOf(kapu));
	}	
}

