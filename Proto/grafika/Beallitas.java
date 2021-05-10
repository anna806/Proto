package grafika;

import java.io.File;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Beallitas {
	/**
	 * Az aszteroidák számát tároló adattag
	 */
	int cnta;
	
	/**
	 * Lista, amelyik a játszani kívánó játékosok neveit tárolja
	 */
	List<String> tnevek;
	
	/**
	 * Az aszteroidaszámot növelõ gomb
	 */
	Button aplusz;
	
	/**
	 * Az aszteroidaszámot csökkentõ gomb
	 */
	Button amin;
	
	/**
	 * A listához telepes hozzáadását végzõ gomb
	 */
	Button tplusz;
	
	/**
	 * A listából telepes eltávolítását végzõ gomb
	 */
	Button tmin;
	
	/**
	 * A fõmenübe való visszatérést szabályzó gomb
	 */
	Button menu;
	
	/**
	 * Felirat, ami a beállított aszteroidák számát jelzi
	 */
	Label aszam;
	
	/**
	 * A listához játékos hozzáadásához tartozó TextField mezõ
	 */
	TextField tnev;
	
	/**
	 * A játékos eltávolításához tartozó TextField mezõ
	 */
	TextField ttorol;
	
	/**
	 * A Stage objektum, amin a jeleneteket megjelenítkük
	 */
	Stage s;
	
	/**
	 * A jelenlegi jelenet
	 */
	Scene scene;
	
	/**
	 * Az ezelõtt megjelenített jelenet
	 */
	Scene oldScene ;
	
	/**
	 * A játékosok neveit megjelenítõ nézet
	 */
	ListView<String> jatlist;
	
	/**
	 * @param stage - a Stage objektum, amin felépítjük a Beállításokhoz tartozó jelenetet
	 * @param oldscene - Az elõzõ jelenet, ahonnan meghívtuk, hogy vissza tudjunk térni oda
	 * @param aszamn - az aszteeroidák száma, ha már be van állítva
	 * @param nevek - nevek listája, akik már játékban vannak. 
	 */
	void felepit(Stage stage, Scene oldscene, List<Integer> aszamn, List<String> nevek) {
		oldScene = oldscene;
		s = stage;
		cnta = aszamn.get(0);
		tnevek = nevek;
		
		GridPane panes = new GridPane();
		panes.setPadding(new Insets(10, 10, 10, 10));
		panes.setVgap(10); 
	    panes.setHgap(20);
	    
	    GridPane aster = new GridPane();									
		aster.setPadding(new Insets(10, 10, 10, 10));
		aster.setVgap(10); 
	    aster.setHgap(5);
	    aster.setStyle("-fx-background-color: BEIGE");
	    
	    Label aszterl = new Label("Aszteroidák száma");
	    aszterl.setStyle("-fx-font-size: 20");
		aszterl.setTextAlignment(TextAlignment.LEFT);
		
		aplusz  = new Button("+");
		aplusz.setStyle("-fx-font-size: 20;-fx-background-color: GREY; -fx-font-weight: bold");
		aplusz.setMaxSize(50, 10);
		aplusz.setOnAction(actionEvent -> {
			cnta++;
			aszam.setText(Integer.toString(cnta));
			aszamn.clear();
			aszamn.add(cnta);
		});
		
		amin = new Button("-");
		amin.setStyle("-fx-font-size: 20;-fx-background-color: GREY; -fx-font-weight: bold");
		amin.setMinSize(50, 10);
		amin.setOnAction(actionEvent -> {
			cnta--;
			aszam.setText(Integer.toString(cnta));
			aszamn.clear();
			aszamn.add(cnta);
		});
		
		aszam = new Label(Integer.toString(cnta));
		aszam.setStyle("-fx-font-size: 20;-fx-background-color: LIGHTGREY");		
		aszam.setMinSize(40, 10);
		aszam.setAlignment(Pos.CENTER);
		
		aster.add(aszterl, 0, 0, 3 , 1);
		aster.add(amin, 0, 1);
		aster.add(aszam, 1, 1);
		aster.add(aplusz, 2, 1);

		GridPane telp = new GridPane();												
		telp.setPadding(new Insets(10, 10, 10, 10));
		telp.setVgap(10); 
	    telp.setHgap(10);
	    telp.setStyle("-fx-background-color: BEIGE");
	    
	    Label telpl = new Label("Játékos hozzáadása");
	    telpl.setStyle("-fx-font-size: 20");
	    telpl.setTextFill(Color.LIMEGREEN);
		telpl.setTextAlignment(TextAlignment.LEFT);
	    
	    tplusz  = new Button("OK");
	    tplusz.setStyle("-fx-font-size: 20;-fx-background-color: GREY; -fx-font-weight: bold");
	    tplusz.setMinSize(60, 10);
	    tplusz.setOnAction(actionEvent -> {
	    	if(tnev.getText().isEmpty()) {
	    		Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Nem adott meg nevet!");
				a.show();
	    	}
	    	else {
				tnevek.add(tnev.getText());
				jatlist.getItems().clear();
				jatlist.getItems().addAll(tnevek);
				tnev.clear();
	    	}
		});
	    
	    tnev = new TextField();
		tnev.setStyle("-fx-font-size: 25;-fx-background-color: LIGHTGREY;");		
		tnev.setMinSize(150, 30);
		
		telp.add(telpl, 0, 0);
		telp.add(tnev, 0, 1);
		telp.add(tplusz, 1, 1);
		
		GridPane telm = new GridPane();												
		telm.setPadding(new Insets(10, 10, 10, 10));
		telm.setVgap(10); 
	    telm.setHgap(10);
	    telm.setStyle("-fx-background-color: BEIGE");
		
	    Label telml = new Label("Játékos törlése");
	    telml.setStyle("-fx-font-size: 20");
	    telml.setTextFill(Color.DARKRED);
		telml.setTextAlignment(TextAlignment.LEFT);
		
		tmin  = new Button("OK");
		tmin.setStyle("-fx-font-size: 20;-fx-background-color: GREY; -fx-font-weight: bold");
		tmin.setMinSize(60, 30);
		tmin.setOnAction(actionEvent -> {
			if(ttorol.getText().isEmpty()) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Nem adott meg nevet!");
				a.show();
			}
			else {
				if(!tnevek.contains(ttorol.getText())) {
					Alert a = new Alert(AlertType.ERROR);
					a.setContentText("Nincs ilyen nevû játékos");
					a.show();
				}
				else {
					tnevek.remove(ttorol.getText());
					jatlist.getItems().clear();
					jatlist.getItems().addAll(tnevek);
					ttorol.clear();
				}
			}
		});
		
		ttorol = new TextField();
		ttorol.setStyle("-fx-font-size: 25;-fx-background-color: LIGHTGREY");		
		ttorol.setMinSize(150, 30);
		
		telm.add(telml, 0, 0);
		telm.add(ttorol, 0, 1);
		telm.add(tmin, 1, 1);
		 
		VBox jatl = new VBox();											
		jatl.setPadding(new Insets(10, 10, 10, 10));
		jatl.setStyle("-fx-background-color: BEIGE");
		 
		Label jatll = new Label("Játékosok");
		jatll.setStyle("-fx-font-size: 20");
		jatll.setTextAlignment(TextAlignment.LEFT);
		 
		jatlist = new ListView<String>();
		jatlist.getItems().addAll(tnevek);
		jatlist.setStyle("-fx-background-color: LIGHTGREY");
		
		jatl.getChildren().addAll(jatll, jatlist);
		 
		menu = new Button("Menü");
		menu.setStyle("-fx-background-color: DARKGOLDENROD; -fx-font-size: 15");
		menu.setTextFill(Color.WHITE);
		menu.setOnAction(actionEvent -> {
			jatlist.getItems().clear();
			s.setScene(oldScene);
		});
		 
		String dir = System.getProperty("user.dir");
    		File dirf = new File(dir);
    		String parentPath = dirf.getParent();
		String imagePath = "file:\\" + parentPath + "\\images\\hatter.png";
		
		Image image = new Image(imagePath);
		BackgroundImage myBI= new BackgroundImage(new Image(imagePath, 1100, 700, false, true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		panes.setStyle("-fx-background-color: GOLDENROD");
		
		panes.add(aster, 0, 0);
		panes.add(telp, 0, 1);
		panes.add(telm, 0, 2);
		panes.add(jatl, 1, 0, 1, 3);
		panes.add(menu, 0, 3);
				
		panes.setMaxSize(750, 500);
		Group p = new Group();
		p.getChildren().add(panes);
		
		BorderPane bp = new BorderPane();
		bp.setCenter(p);
//		bp.setStyle("-fx-background-color: MIDNIGHTBLUE");
		bp.setBackground(new Background(myBI));
	    
		scene = new Scene(bp, 1100, 700);
		s.setScene(scene);
		s.show();
	}
}
