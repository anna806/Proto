package grafika;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import proto.Aszteroidaov;

public class Menusav {
	Button menu;
	ImageView ment;
	Aszteroidaov jatekter;
	Jatekter jtk;
	
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
	
	public void felepit(Aszteroidaov ov, Jatekter j) {
		jatekter = ov;
		jtk = j;
		
		primaryStage = new Scene();
		
		menu = new Button("Mentes");
		menu.setStyle("-fx-background-color: DARKGOLDENROD");
		menu.setTextFill(Color.WHITE);
		menu.setMinSize(70, 45);
		
		redBottom = new TableView();
		
		///??????
		TableColumn<String, String> columnT = new TableColumn<>("Telepes");
	    columnT.setCellValueFactory(new PropertyValueFactory<>("telepes"));
	    TableColumn<String> columnR = new TableColumn<>("Robot");
	    columnR.setCellValueFactory(new PropertyValueFactory<>("robot"));
	    TableColumn<String> columnU = new TableColumn<>("Ufo");
	    columnU.setCellValueFactory(new PropertyValueFactory<>("ufo"));
	    TableColumn<String> columnK = new TableColumn<>("Kapu");
	    columnK.setCellValueFactory(new PropertyValueFactory<>("kapu"));


	    redBottom.getColumns().add(columnT);
	    redBottom.getColumns().add(columnR);
	    redBottom.getColumns().add(columnU);
	    redBottom.getColumns().add(columnK);
	    
	    //milyen object kéne?
	    redBottom.getItems().add("Bori");
	    

	    VBox vbox = new VBox(redBottom);

	    Scene scene = new Scene(vbox);

	   
		primaryStage.setScene(scene);

	    primaryStage.show();
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == asas) {
			jatekter.getAktual().Furas();
		}
		else if(event.getSource() == banyasz) {
			jatekter.getAktual().Banyaszat();
			setLabels(jatekter.getAktual());
		}
		else if(event.getSource() == mozog) {
			jatekter.getAktual().Mozog(jtk.getKivalasztott());	//egérkattintással kiválasztott aszteroida
		}
		else if(event.getSource() == robotep) {
			jatekter.getAktual().RobotEpit();	
		}
		else if(event.getSource() == kapuep) {
			jatekter.getAktual().KapuEpit();
		}
		else if(event.getSource() == bazisep) {
			jtk.getKivalasztott().BazisEpit();
		}
		else if(event.getSource() == visszatolt) {
			jatekter.getAktual().Visszatolt();
			setLabels(jatekter.getAktual());
		}
	}
}
