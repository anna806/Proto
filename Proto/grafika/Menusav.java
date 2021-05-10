package grafika;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import proto.Aszteroida;
import proto.Jatek;
import proto.Main;

public class Menusav extends Pane implements EventHandler<ActionEvent>{
	
	
	/**
	 * Az aktuális aszteroida: az éppen soron lévõ játékos aszteroidája
	 * Választott aszteroida: egérkattintással választott aszteroida
	 */
	Aszteroida aktualis;
	Aszteroida valasztott;
	
	
	/**
	 * A mentés gomb megjelenéséért felelõs tagok
	 */
	Image mentes;
   	ImageView mentesView;
   	
   	/**
   	 * A játéklogika, az eggyel korábbi megjelenés
   	 * a Stage, ami tartalmazza a játék részeit, 
   	 * a játéktér, ami az aszteroidák megjelenítését tartalmazza
   	 */
   	Jatek game;
   	Scene oldscene;
   	Stage primary;
   	Jatekter jatekter;
	
   	
	/**
	 * Jobb felsõ sarok menü és mentés gombja
	 */
	Button menu;
	Button ment;
	
	/**
	 * Piros keretes és zöld keretes táblázat és tartalma
	 */
	AszterG red;
	AszterG green;
	
	/**
	 * Az adott táblázatot tartalmazó VBox, amivel az AszterG visszatér
	 */
	VBox aGreen;
	VBox aRed;
	
	
	
	/**
	 * A Menusav felépítése. Megépíti a menü és mentés gombot, 
	 * a visszakapott piros és zöld tárolókat fölteszi a VBoxra,
	 * majd ezt visszaadja a JátékÁllapotnak.
	 * @param _game: játéklogika elérése
	 * @param _oldscene: megjelenés frissítéséhez szükséges korábbi megjelenés
	 * @param _primary: a megjelenés alapja, amire elemeket pakolunk
	 * @param _jatekter: aszteroidaöv megjelenítése grafikusan
	 * @return mindent tartalmazó VBox
	 */
	public VBox felepit( Jatek _game, Scene _oldscene, Stage _primary, Jatekter _jatekter) {
		
		game = _game;
		oldscene = _oldscene;
	   	primary = _primary;
	   	jatekter = _jatekter;
	   	
	   	String dir = System.getProperty("user.dir");
    	File dirf = new File(dir);
    	String parentPath = dirf.getParent();
		String imagePath = "file:\\" + parentPath + "\\images\\flop.png";
		mentes = new Image(imagePath); 
		mentesView = new ImageView();
		mentesView.setImage(mentes);
		ment = new Button();
		ment.setGraphic(mentesView);
		ment.setStyle("-fx-background-color: DARKGOLDENROD");
		ment.setOnAction(this);

		menu = new Button("Menü");
		menu.setStyle("-fx-background-color: DARKGOLDENROD; -fx-font-size: 20;");
		menu.setTextFill(Color.WHITE);
		menu.setMinSize(70, 45);
		menu.setOnAction(this);
		
		FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(menu);
        flowpane.getChildren().add(ment);
        flowpane.setStyle("-fx-background-color: DARKGOLDENROD");
		
		VBox base = new VBox();
		
		aktualis = Main.game.GetOv().getAktual().getAszteroida();
		if(jatekter.getKivalasztott() != null)
			valasztott = jatekter.getKivalasztott().getAszteroida();
		else 
			valasztott = null;
		
		red = new AszterG(aktualis);
		green = new AszterG(valasztott);
		
		aRed = red.felepit( "RED");
		aGreen = green.felepit("GREEN");
		
		base.getChildren().addAll(flowpane, aRed, aGreen);
		base.setStyle("-fx-background-color: GOLDENROD");
		
		return base;
	}

	/**
	 * Frissíti a piros vagy zöld táblázatot a paraméterektõl függõen,
	 * a paraméterben kapott AszteroidaView értékei szerint
	 * @param a: ennek az aszteroidának lesznek az értékei a táblázatban
	 * @param kivalasztott: ha igaz a zöld, ha hamis a piros táblázatot kell frissíteni
	 */
	public void Update(AszteroidaView a, boolean kivalasztott) {
		if(kivalasztott) 
			green.setLabelsRed(a.getAszteroida());
		else
			red.setLabelsRed(a.getAszteroida());
	}

	/**
	 * Menü gombra nyomáskor megjelenik a menü és eltûnik a játéktér,
	 * mentéskor kimenti egy fileba a játék aktuális állapotát
	 */
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == menu) {
			primary.setScene(oldscene);
		} else if(event.getSource() == ment) {
			game.ser(game.GetOv(), "jatek.txt");
		}
	}
}
