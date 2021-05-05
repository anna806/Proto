package grafika;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Fomenu implements EventHandler<ActionEvent> {
	Button indit;
	Button folyt;
	Button beall;
	Button kilep;
	Label cim;
	
	List<String> nevek;
	int aszam;
	
	public VBox felepit() {
		
		VBox root = new VBox(5);
		
		indit  = new Button("Ind�t�s");
		indit.setStyle("-fx-background-color: DARKGOLDENROD");
		indit.setTextFill(Color. GOLDENROD);
		indit.setMinSize(140, 60);
		indit.setOnAction(this);
		
		folyt  = new Button("Folytat�s");
		folyt.setStyle("-fx-background-color: DARKGOLDENROD");
		folyt.setTextFill(Color. GOLDENROD);
		folyt.setMinSize(140, 60);
		folyt.setOnAction(this);
		
		beall  = new Button("Be�ll�t�sok");
		beall.setStyle("-fx-background-color: DARKGOLDENROD");
		beall.setTextFill(Color. GOLDENROD);
		beall.setMinSize(140, 60);
		beall.setOnAction(this);
		
		kilep  = new Button("Kil�p�s");
		kilep.setStyle("-fx-background-color: DARKGOLDENROD");
		kilep.setTextFill(Color. GOLDENROD);
		kilep.setMinSize(140, 60);
		kilep.setOnAction(this);
		
		cim = new Label("Aszteroidab�ny�szat");
		cim.setStyle("-fx-font-size: 50");
		cim.setTextAlignment(TextAlignment.CENTER);
		
		root.getChildren().addAll(cim, indit, folyt, beall, kilep);
		root.setAlignment(Pos.CENTER);
		
		return root;	
	}
	
	@Override
	public void handle(ActionEvent event){
		if(event.getSource() == indit) {
			
		}
		else if(event.getSource() == folyt) {
			
		}
		else if(event.getSource() == beall) {
			
		}
		else if(event.getSource() == kilep) {
			//???
		}
	}
}
