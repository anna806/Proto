package grafika;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import proto.Aszteroida;
import proto.Aszteroidaov;

public class Menusav extends Pane implements EventHandler<ActionEvent>{
	
	private AszterG aktualis;
	private AszterG valasztott;
	
	//FELÉPÍTÉSI TERV
	/*
	 * 2 db gridpane
	 * 1 db borderpane
	 * 
	 * mentés button
	 * menü button
	 * 
	 * Piros:
	 * 6 db label fentre
	 * 4+3 db label lentre
	 * 1 db listview 
	 * 
	 * Zöldre ugyanez:
	 */
	
	Scene primaryStage;
	
	Button menu;
	ImageView ment;
	
	Label rt1_a;
   	Label rt1_b;
   	Label rt2_a;
   	Label rt2_b;
   	Label rt3_a;
   	Label rt3_b;
   	
   	Label rb1_a;
   	ListView rb1_b;
   	Label rb2_a;
   	Label rb2_b;
   	Label rb3_a;
   	Label rb3_b;
   	Label rb4_a;
   	Label rb4_b;
		
   	Label gt1_a;
   	Label gt1_b;
   	Label gt2_a;
   	Label gt2_b;
   	Label gt3_a;
   	Label gt3_b;
   	
   	Label gb1_a;
   	ListView gb1_b;
   	Label gb2_a;
   	Label gb2_b;
   	Label gb3_a;
   	Label gb3_b;
   	Label gb4_a;
   	Label gb4_b;
   	
   	Image mentes;
   	ImageView mentesView;
   	
   	Stage primary;
   	Scene oldscene;
   
   	private List<String> nevek;
   	
   	Menusav(Stage _primary, Scene _oldscene, List<String> _nevek){
   		primary = _primary;
   		oldscene = _oldscene;
   		
   		nevek = _nevek;
   	}
   	
	public void felepit(Aszteroidaov ov, Jatekter j) {
				
		mentes = new Image("flop.png"); //kell könyvtár----------------------------------
		mentesView = new ImageView();
		mentesView.setImage(mentes);
		
		//egészet egy panebe
		
		menu = new Button("Menü");
		menu.setStyle("-fx-background-color: DARKGOLDENROD");
		menu.setTextFill(Color.WHITE);
		menu.setMinSize(70, 45);
		
		//RED TABLE----------------------------------------------
		
		GridPane rootR = new GridPane();
		 
	    rootR.setPadding(new Insets(20));
	    rootR.setHgap(25);
	    rootR.setVgap(15);
	 
	       
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
	   	rootR.add(rt1_a, 3, 0);
	   	rootR.add(rt2_a, 3, 1);
	   	rootR.add(rt3_a, 3, 2);
	   	
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
       setLabelsRed(aktualis.GetAszteroida(), nevek);
	    
	  //RED TABLE----------------------------------------------
	 
	   	GridPane rootG = new GridPane();
		 
	    rootG.setPadding(new Insets(20));
	    rootG.setHgap(25);
	    rootG.setVgap(15);
	       
	    gt1_a = new Label("Kéregvastagság");
	   	gt1_b = new Label("");
	   	gt2_a = new Label("Napközel");
	   	gt2_b = new Label("");
	   	gt3_a = new Label("Napvihar");
	   	gt3_b = new Label("");
	   	
	   	gb1_a = new Label("Telepes");
	   	gb1_b = new ListView<String>();
	   	gb2_a = new Label("Robot");
	   	gb2_b = new Label("");
	   	gb3_a = new Label("Ufó");
	   	gb3_b = new Label("");
	   	gb4_a = new Label("Kapu");
	   	gb4_b = new Label("");
	 
	   	//fenti címek
	   	GridPane.setHalignment(gt1_a, HPos.LEFT);
	   	GridPane.setHalignment(gt2_a, HPos.LEFT);
	   	GridPane.setHalignment(gt3_a, HPos.LEFT);
	   	rootG.add(gt1_a, 0, 0, 3, 1);
	   	rootG.add(gt2_a, 0, 1, 3, 1);
	   	rootG.add(gt3_a, 0, 2, 3, 1);
	       
	   	//fenti értékek
	   	GridPane.setHalignment(gt1_b, HPos.LEFT);
	   	GridPane.setHalignment(gt2_b, HPos.LEFT);
	   	GridPane.setHalignment(gt3_b, HPos.LEFT);
	   	rootG.add(gt1_a, 3, 0);
	   	rootG.add(gt2_a, 3, 1);
	   	rootG.add(gt3_a, 3, 2);
	   	
	   	//lenti címek
	   	GridPane.setHalignment(gb1_a, HPos.CENTER);
	   	GridPane.setHalignment(gb2_a, HPos.CENTER);
	   	GridPane.setHalignment(gb3_a, HPos.CENTER);
	   	GridPane.setHalignment(gb4_a, HPos.CENTER);
	   	rootG.add(gb1_a, 0, 3, 1, 6);
	   	rootG.add(gb2_a, 1, 3);
	   	rootG.add(gb3_a, 2, 3);
	   	rootG.add(gb3_a, 3, 3);
	   	
	   	setLabelsGreen(valasztott.GetAszteroida(), nevek);
	   	
	       /*Scene scene = new Scene(root, 300, 300);
	       primaryStage.setTitle("GridPanel Layout Demo (o7planning.org)");
	       primaryStage.setScene(scene);
	       primaryStage.show();*/
	   	
	   	menu.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        //átlép a másik nézetbe
		    }
		});
		
		
	}
	//mentés!!!
	public void handle(ActionEvent event) {
		if(event.getSource() == ment) {
			jatekter.getAktual().Furas();
		}
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
	   	for(String nev: nevek) {
	   		rb1_b.getItems().add(nev);
	   	}
	   	rb2_b.setText(String.valueOf(robot));
	   	rb3_b.setText(String.valueOf(ufo));
	   	rb4_b.setText(String.valueOf(kapu));
	}
	
	public void setLabelsGreen(Aszteroida a, List<String> nevek) {
		gt1_b.setText(String.valueOf(a.getKopenyVastagsag()));
		if(a.getNapkozel() == "true") {
			gt2_b.setText("+");
		} else {
			gt2_b.setText("-");
		}
		
		int robot = 0;
		int ufo = 0;
		int kapu = 0;
		
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
	   	for(String nev: nevek) {
	   		gb1_b.getItems().add(nev);
	   	}
	   	gb2_b.setText(String.valueOf(robot));
	   	gb3_b.setText(String.valueOf(ufo));
	   	gb4_b.setText(String.valueOf(kapu));
	}
}
