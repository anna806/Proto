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
import proto.Main;

public class Fomenu implements EventHandler<ActionEvent> {
	/**
	 * A j�t�kot elind�t� gomb
	 */
	Button indit;
	/**
	 * A j�t�k folytat�sa gomb
	 */
	Button folyt;
	/**
	 * A j�t�k be�ll�t�sok gomb
	 */
	Button beall;
	/**
	 * A kil�p�s gomb
	 */
	Button kilep;
	/**
	 * A j�t�k c�me ki�r�s
	 */
	Label cim;
	
	/**
	 * A l�trehozand� telepesek neveit t�rolja
	 */
	List<String> nevek = new ArrayList<String>();
	/**
	 * A l�trehozand� aszteroid�k sz�m�t t�rolja
	 */
	List<Integer> aszam = new ArrayList<Integer>();
	Stage s;
	Scene scene;
	
	/**
	 * Fel�p�ti a f�men�t, be�ll�tja a gombokat �s a c�met, majd belerakja a f�ablakba
	 * @param stage : a f�ablak
	 */
	public void felepit(Stage stage) {
		s = stage;
		VBox root = new VBox(10);
		aszam.add(10);
		indit  = new Button("Ind�t�s");
		indit.setStyle("-fx-background-color: DARKGOLDENROD; -fx-font-size: 25");
		indit.setTextFill(Color.WHITE);
		indit.setMinSize(200, 40);
		indit.setOnAction(this);
		
		folyt  = new Button("Folytat�s");
		folyt.setStyle("-fx-background-color: DARKGOLDENROD; -fx-font-size: 25");
		folyt.setTextFill(Color.WHITE);
		folyt.setMinSize(200, 40);
		folyt.setOnAction(this);
		
		beall  = new Button("Be�ll�t�sok");
		beall.setStyle("-fx-background-color: DARKGOLDENROD; -fx-font-size: 25");
		beall.setTextFill(Color.WHITE);
		beall.setMinSize(200, 40);
		beall.setOnAction(this);
		
		kilep  = new Button("Kil�p�s");
		kilep.setStyle("-fx-background-color: DARKGOLDENROD; -fx-font-size: 25");
		kilep.setTextFill(Color.WHITE);
		kilep.setMinSize(200, 40);
		kilep.setOnAction(this);
		
		cim = new Label("Aszteroidab�ny�szat");
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
		BackgroundImage myBI= new BackgroundImage(new Image(imagePath, 900, 700, false, true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		root.setBackground(new Background(myBI));
		
		scene = new Scene(root, 900, 700);
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
	
	/**
	 * Az egyes gombok �ltal kiv�ltott esem�nyeket kezeli
	 */
	@Override
	public void handle(ActionEvent event){
		if(event.getSource() == indit) {
			
			nevek.add("Valaki");
			JatekAllapot jtk = new JatekAllapot(aszam, nevek);
			jtk.felepit(s, scene, aszam, nevek);
		}
		else if(event.getSource() == folyt) {
			JatekAllapot jtk = new JatekAllapot(aszam, nevek);
			jtk.felepit(s, scene, aszam, nevek);
		}
		else if(event.getSource() == beall) {
			Beallitas b = new Beallitas();
			aszam.add(0);
			b.felepit(s, scene, aszam, nevek);
		}
		else if(event.getSource() == kilep) {
			s.close();
			Platform.exit();
		    System.exit(0);
		}
	}
}
