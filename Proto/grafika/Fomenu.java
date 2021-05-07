package grafika;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	
	List<String> nevek = new ArrayList<String>();
	int aszam = 0;
	Stage s;
	Scene scene;
	
	public void felepit(Stage stage) {
		s = stage;
		VBox root = new VBox(10);
		
		indit  = new Button("Indítás");
		indit.setStyle("-fx-background-color: DARKGOLDENROD; -fx-font-size: 25");
		indit.setTextFill(Color.WHITE);
		indit.setMinSize(200, 40);
		indit.setOnAction(this);
		
		folyt  = new Button("Folytatás");
		folyt.setStyle("-fx-background-color: DARKGOLDENROD; -fx-font-size: 25");
		folyt.setTextFill(Color.WHITE);
		folyt.setMinSize(200, 40);
		folyt.setOnAction(this);
		
		beall  = new Button("Beállítások");
		beall.setStyle("-fx-background-color: DARKGOLDENROD; -fx-font-size: 25");
		beall.setTextFill(Color.WHITE);
		beall.setMinSize(200, 40);
		beall.setOnAction(this);
		
		kilep  = new Button("Kilépés");
		kilep.setStyle("-fx-background-color: DARKGOLDENROD; -fx-font-size: 25");
		kilep.setTextFill(Color.WHITE);
		kilep.setMinSize(200, 40);
		kilep.setOnAction(this);
		
		cim = new Label("Aszteroidabányászat");
		cim.setStyle("-fx-font-size: 50");
		cim.setTextFill(Color.WHITE);
		cim.setTextAlignment(TextAlignment.CENTER);
		
		root.getChildren().addAll(cim, indit, folyt, beall, kilep);
		root.setAlignment(Pos.CENTER);
		
		String dir = System.getProperty("user.dir");
    	File dirf = new File(dir);
    	String parentPath = dirf.getParent();
		String imagePath = "file:\\" + parentPath + "\\images\\hatter.png";
		
		Image image = new Image(imagePath);
		BackgroundImage myBI= new BackgroundImage(new Image(imagePath, 800, 600, false, true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
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
