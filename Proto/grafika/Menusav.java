package grafika;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import proto.Aszteroida;
import proto.Aszteroidaov;
import proto.Jatek;

public class Menusav extends Pane implements EventHandler<ActionEvent>{
	
	Aszteroida aktualis;
	Aszteroida valasztott;
	
	Image mentes;
   	ImageView mentesView;
   	
   	Jatek game;
   	Scene oldscene;
   	Stage primary;
	
	Button menu;
	
public VBox felepit(List<String> nevek, Jatek _game, Scene _oldscene, Stage _primary) {
		
		game = _game;
		oldscene = _oldscene;
	   	primary = _primary;
		
		mentes = new Image("file:flop.png"); //kell könyvtár----------------------------------
		mentesView = new ImageView();
		mentesView.setImage(mentes);

		menu = new Button("Menü");
		menu.setStyle("-fx-background-color: DARKGOLDENROD");
		menu.setTextFill(Color.WHITE);
		menu.setMinSize(70, 45);
		
		FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(menu);
        flowpane.getChildren().add(mentesView);
		
		VBox base = new VBox();
		base.setAlignment(Pos.BASELINE_CENTER);
		
		aktualis = game.GetOv().getAktual().getAszteroida();
		
		AszterG red = new AszterG(aktualis);
		AszterG green = new AszterG(valasztott);
		
		VBox aRed = red.felepit(nevek);
		VBox aGreen = green.felepit(nevek);
		
		base.getChildren().addAll(flowpane, aRed, aGreen);
		
		return base;
}

		@Override
		public void handle(ActionEvent event) {
			if(event.getSource() == menu) {
				primary.setScene(oldscene);
			} else if(event.getSource() == mentesView) {
				game.ser(game.GetOv(), "jatek.txt");
			}
		}
}
