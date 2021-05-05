package grafika;

import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Fomenu implements EventHandler<ActionEvent> {
	Button indit;
	Button folyt;
	Button beall;
	Button kilep;
	Label cim;
	
	List<String> nevek;
	int aszam;
	Stage s;
	Scene scene;
	
//	public VBox felepit() {
	public void felepit(Stage stage) {
		s = stage;
		VBox root = new VBox(5);
		
		indit  = new Button("Indítás");
		indit.setStyle("-fx-background-color: DARKGOLDENROD");
		indit.setTextFill(Color.WHITE);
		indit.setMinSize(140, 60);
		indit.setOnAction(this);
		
		folyt  = new Button("Folytatás");
		folyt.setStyle("-fx-background-color: DARKGOLDENROD");
		folyt.setTextFill(Color.WHITE);
		folyt.setMinSize(140, 60);
		folyt.setOnAction(this);
		
		beall  = new Button("Beállítások");
		beall.setStyle("-fx-background-color: DARKGOLDENROD");
		beall.setTextFill(Color.WHITE);
		beall.setMinSize(140, 60);
		beall.setOnAction(this);
		
		kilep  = new Button("Kilépés");
		kilep.setStyle("-fx-background-color: DARKGOLDENROD");
		kilep.setTextFill(Color.WHITE);
		kilep.setMinSize(140, 60);
		kilep.setOnAction(this);
		
		cim = new Label("Aszteroidabányászat");
		cim.setStyle("-fx-font-size: 50");
		cim.setTextAlignment(TextAlignment.CENTER);
		
		root.getChildren().addAll(cim, indit, folyt, beall, kilep);
		root.setAlignment(Pos.CENTER);
		
//		root.setStyle("-fx-background-color: GOLDENROD");
		BackgroundImage myBI= new BackgroundImage(new Image("hatter.png",32,32,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		//then you set to your node
		root.setBackground(new Background(myBI));
		
		scene = new Scene(root, 800, 600);
		s.setTitle("Aszteroidabanyaszat");
		s.setScene(scene);
		s.show();
		s.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
		
//		return root;	
	}
	
	@Override
	public void handle(ActionEvent event){
		if(event.getSource() == indit) {
			JatekAllapot jtk = new JatekAllapot();
			jtk.felepit(s, scene, aszam, nevek);
		}
		else if(event.getSource() == folyt) {
			JatekAllapot jtk = new JatekAllapot();
			jtk.felepit(s, scene, aszam, nevek);
		}
		else if(event.getSource() == beall) {
			Beallitas b = new Beallitas();
			b.felepit(s, scene, aszam, nevek);
		}
		else if(event.getSource() == kilep) {
			s.close();
			Platform.exit();
		    System.exit(0);
		}
	}
}
