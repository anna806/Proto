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
	 * Az aktu�lis aszteroida: az �ppen soron l�v� j�t�kos aszteroid�ja
	 * V�lasztott aszteroida: eg�rkattint�ssal v�lasztott aszteroida
	 */
	Aszteroida aktualis;
	Aszteroida valasztott;
	
	
	/**
	 * A ment�s gomb megjelen�s��rt felel�s tagok
	 */
	Image mentes;
   	ImageView mentesView;
   	
   	/**
   	 * A j�t�klogika, az eggyel kor�bbi megjelen�s
   	 * a Stage, ami tartalmazza a j�t�k r�szeit, 
   	 * a j�t�kt�r, ami az aszteroid�k megjelen�t�s�t tartalmazza
   	 */
   	Jatek game;
   	Scene oldscene;
   	Stage primary;
   	Jatekter jatekter;
	
   	
	/**
	 * Jobb fels� sarok men� �s ment�s gombja
	 */
	Button menu;
	Button ment;
	
	/**
	 * Piros keretes �s z�ld keretes t�bl�zat �s tartalma
	 */
	AszterG red;
	AszterG green;
	
	/**
	 * Az adott t�bl�zatot tartalmaz� VBox, amivel az AszterG visszat�r
	 */
	VBox aGreen;
	VBox aRed;
	
	
	
	/**
	 * A Menusav fel�p�t�se. Meg�p�ti a men� �s ment�s gombot, 
	 * a visszakapott piros �s z�ld t�rol�kat f�lteszi a VBoxra,
	 * majd ezt visszaadja a J�t�k�llapotnak.
	 * @param _game: j�t�klogika el�r�se
	 * @param _oldscene: megjelen�s friss�t�s�hez sz�ks�ges kor�bbi megjelen�s
	 * @param _primary: a megjelen�s alapja, amire elemeket pakolunk
	 * @param _jatekter: aszteroida�v megjelen�t�se grafikusan
	 * @return mindent tartalmaz� VBox
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

		menu = new Button("Men�");
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
	 * Friss�ti a piros vagy z�ld t�bl�zatot a param�terekt�l f�gg�en,
	 * a param�terben kapott AszteroidaView �rt�kei szerint
	 * @param a: ennek az aszteroid�nak lesznek az �rt�kei a t�bl�zatban
	 * @param kivalasztott: ha igaz a z�ld, ha hamis a piros t�bl�zatot kell friss�teni
	 */
	public void Update(AszteroidaView a, boolean kivalasztott) {
		if(kivalasztott) 
			green.setLabelsRed(a.getAszteroida());
		else
			red.setLabelsRed(a.getAszteroida());
	}

	/**
	 * Men� gombra nyom�skor megjelenik a men� �s elt�nik a j�t�kt�r,
	 * ment�skor kimenti egy fileba a j�t�k aktu�lis �llapot�t
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
