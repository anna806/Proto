package grafika;

import java.io.File;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	Label aszam;
	TextField tnev;
	TextField ttorol;
	Stage s;
	Scene scene;
	Scene oldScene ;
	
	ListView<String> jatlist;
	
	void felepit(Stage stage, Scene oldscene, List<Integer> aszamn, List<String> nevek) {
		oldScene = oldscene;
		s = stage;
		cnta = aszamn.get(0);
		tnevek = nevek;
		
		GridPane panes = new GridPane();
		panes.setPadding(new Insets(10, 10, 10, 10));
		panes.setVgap(10); 
	    panes.setHgap(20);
	    
	    
	    GridPane aster = new GridPane();									//aszteroida be�ll�t�sa
		aster.setPadding(new Insets(10, 10, 10, 10));
		aster.setVgap(5); 
	    aster.setHgap(5);
	    aster.setStyle("-fx-background-color: BEIGE");
	    
	    Label aszterl = new Label("Aszteroid�k sz�ma");
	    aszterl.setStyle("-fx-font-size: 20");
		aszterl.setTextAlignment(TextAlignment.LEFT);
		
		aplusz  = new Button("+");
		aplusz.setStyle("-fx-font-size: 30;-fx-background-color: GREY;");
		aplusz.setMinSize(20, 10);
		aplusz.setOnAction(actionEvent -> {
			cnta++;
			aszam.setText(Integer.toString(cnta));
			aszamn.clear();
			aszamn.add(cnta);
		});
		
		amin  = new Button("-");
		amin.setStyle("-fx-font-size: 30;-fx-background-color: GREY;");
		amin.setMinSize(20, 10);
		amin.setOnAction(actionEvent -> {
			cnta--;
			aszam.setText(Integer.toString(cnta));
			aszamn.clear();
			aszamn.add(cnta);
		});
		
		aszam = new Label(Integer.toString(cnta));
		aszam.setStyle("-fx-font-size: 30;-fx-background-color: LIGHTGREY;");		//itt kell kezelni actiont?
		aszam.setMinSize(20, 10);
		
		aster.add(aszterl, 0, 0, 3 , 1);
		aster.add(amin, 0, 1);
		aster.add(aszam, 1, 1);
		aster.add(aplusz, 2, 1);

		GridPane telp = new GridPane();												// j�t�kos hozz�ad
		telp.setPadding(new Insets(10, 10, 10, 10));
		telp.setVgap(5); 
	    telp.setHgap(10);
	    telp.setStyle("-fx-background-color: BEIGE");
	    
	    Label telpl = new Label("J�t�kos hozz�ad�sa");
	    telpl.setStyle("-fx-font-size: 20");
	    telpl.setTextFill(Color.LAWNGREEN);
		telpl.setTextAlignment(TextAlignment.LEFT);
	    
	    tplusz  = new Button("OK");
	    tplusz.setStyle("-fx-font-size: 30;-fx-background-color: GREY;");
	    tplusz.setMinSize(60, 10);
	    tplusz.setOnAction(actionEvent -> {
	    	if(tnev.getText()!="") {
				tnevek.add(tnev.getText());
				jatlist.getItems().clear();
				jatlist.getItems().addAll(tnevek);
				tnev.clear();
	    	}
		});
	    
	    tnev = new TextField();
		tnev.setStyle("-fx-font-size: 25;-fx-background-color: LIGHTGREY;");		//itt kell kezelni actiont?
		tnev.setMinSize(150, 30);
		
		telp.add(telpl, 0, 0);
		telp.add(tnev, 0, 1);
		telp.add(tplusz, 1, 1);
		
		GridPane telm = new GridPane();												// j�t�kos t�rl�se
		telm.setPadding(new Insets(10, 10, 10, 10));
		telm.setVgap(5); 
	    telm.setHgap(10);
	    telm.setStyle("-fx-background-color: BEIGE");
		
	    Label telml = new Label("J�t�kos t�rl�se");
	    telml.setStyle("-fx-font-size: 20");
	    telml.setTextFill(Color.DARKRED);
		telml.setTextAlignment(TextAlignment.LEFT);
		
		tmin  = new Button("OK");
		tmin.setStyle("-fx-font-size: 30;-fx-background-color: GREY;");
		tmin.setMinSize(60, 30);
		tmin.setOnAction(actionEvent -> {
			if(!tnevek.contains(ttorol.getText())) {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Nincs ilyen nev� j�t�kos");
				a.show();
			}
			else {
				tnevek.remove(ttorol.getText());
				jatlist.getItems().clear();
				jatlist.getItems().addAll(tnevek);
				ttorol.clear();
			}
		});
		
		ttorol = new TextField();
		ttorol.setStyle("-fx-font-size: 25;-fx-background-color: LIGHTGREY;");		//itt kell kezelni actiont?
		ttorol.setMinSize(150, 30);
		
		telm.add(telml, 0, 0);
		telm.add(ttorol, 0, 1);
		telm.add(tmin, 1, 1);
		 
		VBox jatl = new VBox();											// j�t�kosok list�ja
		jatl.setPadding(new Insets(10, 10, 10, 10));
		jatl.setStyle("-fx-background-color: BEIGE");
		 
		Label jatll = new Label("J�t�kosok");
		jatll.setStyle("-fx-font-size: 20");
		jatll.setTextAlignment(TextAlignment.LEFT);
		 
		jatlist = new ListView<String>();
		jatlist.getItems().addAll(tnevek);
		
		jatl.getChildren().addAll(jatll, jatlist);
		 
		menu = new Button("Menu");
		menu.setStyle("-fx-background-color: DARKGOLDENROD");
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
		BackgroundImage myBI= new BackgroundImage(new Image(imagePath, 800, 600, false, true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
//		panes.setBackground(new Background(myBI));
		panes.setStyle("-fx-background-color: GOLDENROD");
		
//		HBox hbox = new HBox(20);
//		hbox.setMaxSize(400, 370);
//	    
//		VBox vbox = new VBox(10);
//		vbox.getChildren().addAll(aster, telp, telm);
//		
//		VBox vb = new VBox(10);
//		vb.getChildren().addAll(menu, jatl);
//		hbox.getChildren().addAll(vbox, vb);
//		hbox.setStyle("-fx-background-color: GOLDENROD");
		
		panes.add(aster, 0, 0);
		panes.add(telp, 0, 1);
		panes.add(telm, 0, 2);
		panes.add(jatl, 1, 0, 1, 3);
		panes.add(menu, 0, 3);
		panes.setAlignment(Pos.CENTER);
		panes.setPrefSize(10, 10);
	    panes.setMaxSize(10, 10);
//		BorderPane bp = new BorderPane();
//		bp.getChildren().add(panes);
//		bp.setCenter(panes);
//		bp.setCenter(hbox);
//		bp.setBackground(new Background(myBI));
//		hbox.setAlignment(Pos.CENTER);
		
		scene = new Scene(panes, 800, 600);
//		scene.setFill(Color.DARKBLUE);
		s.setScene(scene);
		s.show();
	}
	
	@Override
	public void handle(ActionEvent event){
	}
}
