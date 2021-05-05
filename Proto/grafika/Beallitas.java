package grafika;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Beallitas implements EventHandler<ActionEvent>{
	int cnta;
	List<String> tnevek;
	Button aplusz;
	Button amin;
	Button tplusz;
	Button tmin;
	Button menu;
	TextField aszam;
	TextField tnev;
	TextField ttorol;
	Stage s;
	Scene scene;
	Scene oldScene ;
	
	void felepit(Stage stage, Scene oldscene, int aszamn, List<String> nevek) {
		oldScene = oldscene;
		s = stage;
		GridPane panes = new GridPane();
		panes.setPadding(new Insets(10, 10, 10, 10));
		panes.setVgap(5); 
	    panes.setHgap(10);
	    
	    GridPane aster = new GridPane();									//aszteroida beállítása
		aster.setPadding(new Insets(10, 10, 10, 10));
		aster.setVgap(5); 
	    aster.setHgap(5);
	    aster.setStyle("-fx-background-color: BEIGE");
	    
	    Label aszterl = new Label("Aszteroidák száma");
	    aszterl.setStyle("-fx-font-size: 20");
		aszterl.setTextAlignment(TextAlignment.LEFT);
		
		aplusz  = new Button("+");
		aplusz.setStyle("-fx-font-size: 30;-fx-background-color: GREY;");
		aplusz.setMinSize(30, 30);
		aplusz.setOnAction(this);
		
		amin  = new Button("-");
		amin.setStyle("-fx-font-size: 30;-fx-background-color: GREY;");
		amin.setMinSize(30, 30);
		amin.setOnAction(this);
		
		aszam = new TextField(Integer.toString(aszamn));
		aszam.setStyle("-fx-font-size: 25;-fx-background-color: LIGHTGREY;");		//itt kell kezelni actiont?
		
		aster.add(aszterl, 0, 0, 3 , 1);
		aster.add(aplusz, 0, 1);
		aster.add(aszam, 1, 1);
		aster.add(amin, 2, 1);

		GridPane telp = new GridPane();												// játékos hozzáad
		telp.setPadding(new Insets(10, 10, 10, 10));
		telp.setVgap(5); 
	    telp.setHgap(10);
	    telp.setStyle("-fx-background-color: BEIGE");
	    
	    Label telpl = new Label("Játékos hozzáadása");
	    telpl.setStyle("-fx-font-size: 20");
	    telpl.setTextFill(Color.LAWNGREEN);
		telpl.setTextAlignment(TextAlignment.LEFT);
	    
	    tplusz  = new Button("OK");
	    tplusz.setStyle("-fx-font-size: 30;-fx-background-color: GREY;");
	    tplusz.setMinSize(60, 30);
	    tplusz.setOnAction(this);
	    
	    tnev = new TextField();
		tnev.setStyle("-fx-font-size: 25;-fx-background-color: LIGHTGREY;");		//itt kell kezelni actiont?
		tnev.setMinSize(150, 30);
		
		telp.add(telpl, 0, 0);
		telp.add(tnev, 0, 1);
		telp.add(tplusz, 1, 1);
		
		GridPane telm = new GridPane();												// játékos törlése
		telm.setPadding(new Insets(10, 10, 10, 10));
		telm.setVgap(5); 
	    telm.setHgap(10);
	    telm.setStyle("-fx-background-color: BEIGE");
		
	    Label telpm = new Label("Játékos törlése");
	    telpm.setStyle("-fx-font-size: 20");
	    telpm.setTextFill(Color.DARKRED);
		telpm.setTextAlignment(TextAlignment.LEFT);
		
		 tmin  = new Button("OK");
		 tmin.setStyle("-fx-font-size: 30;-fx-background-color: GREY;");
		 tmin.setMinSize(60, 30);
		 tmin.setOnAction(this);
		    
		 ttorol = new TextField();
		 ttorol.setStyle("-fx-font-size: 25;-fx-background-color: LIGHTGREY;");		//itt kell kezelni actiont?
		 ttorol.setMinSize(150, 30);
		 
		 GridPane jatl = new GridPane();											// játékosok listája
		 jatl.setPadding(new Insets(10, 10, 10, 10));
		 jatl.setVgap(5); 
		 jatl.setHgap(5);
		 jatl.setStyle("-fx-background-color: BEIGE");
		 
		 Label jatll = new Label("Játékosok");
		 telpm.setStyle("-fx-font-size: 20");
		 telpm.setTextAlignment(TextAlignment.LEFT);
		 
		 menu = new Button("Menu");
		 menu.setOnAction(this);
		 
		 
		 // ide kéne a játékosok listája, kiíró cucc
		 
		 panes.add(aster, 0, 0);
		 panes.add(telp, 0, 1);
		 panes.add(telm, 0, 2);
		 panes.add(jatl, 1, 0, 1, 3);
		 panes.add(menu, 1, 2);
		 
		 scene = new Scene(panes, 800, 600);
		 s.setScene(scene);
		 s.show();
	}
	
	@Override
	public void handle(ActionEvent event){
		if(event.getSource() == aplusz) {
			cnta++;
			aszam.setText(Integer.toString(cnta));
		}
		else if(event.getSource() == amin) {
			cnta--;
			aszam.setText(Integer.toString(cnta));
		}
		else if(event.getSource() == tplusz) {
			if(tnev.getText()!="")
				tnevek.add(tnev.getText());
		}
		else if(event.getSource() == tmin) {
			if(!tnevek.remove(tnev.getText())) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Nincs ilyen nevû játékos");
				a.show();
			}
		}
		else if(event.getSource() == menu) {
			s.setScene(oldScene);
		}
	}
}
