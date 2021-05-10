package grafika;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import proto.Aszteroida;
import proto.Aszteroidaov;
import proto.Main;
import proto.Szen;
import proto.Telepes;
import proto.Uran;
import proto.Vas;
import proto.Vizjeg;


public class Muveletsav extends Pane implements EventHandler<ActionEvent>{
	
	/**
	 * Az �s�s f�ggv�nyt megh�v� gomb
	 */
	Button asas;
	/**
	 * A b�ny�sz�s f�ggv�nyt megh�v� gomb
	 */
	Button banyasz;
	/**
	 * A mozg�s f�ggv�nyt megh�v� gomb
	 */
	Button mozog;
	/**
	 * A robot�p�t�s f�ggv�nyt megh�v� gomb
	 */
	Button robotep;
	/**
	 * A kapu�p�t�s f�ggv�nyt megh�v� gomb
	 */
	Button kapuep;
	/**
	 * A b�zis�p�t�s f�ggv�nyt megh�v� gomb
	 */
	Button bazisep;
	/**
	 * A visszat�lt f�ggv�nyt megh�v� gomb
	 */
	Button visszatolt;
	/**
	 * A vasat jelk�pez� k�r
	 */
	Circle ny1;	
	/**
	 * Az ur�nt jelk�pez� k�r
	 */
	Circle ny2;	
	/**
	 * A v�zjeget jelk�pez� k�r
	 */
	Circle ny3;	
	/**
	 * A szenet jelk�pez� k�r
	 */
	Circle ny4;
	/**
	 * A kaput jelk�pez� t�glalap
	 */
	Rectangle kapu;
	/**
	 * Az aktu�lis j�t�kos nev�t ki�r� label
	 */
	Label jatekos;
	/**
	 * A vas nev�t ki�r� label
	 */
	Label ny1sz;
	/**
	 * Az ur�n nev�t ki�r� label
	 */
	Label ny2sz;
	/**
	 * A v�zj�g nev�t ki�r� label
	 */
	Label ny3sz;
	/**
	 * A sz�n nev�t ki�r� label
	 */
	Label ny4sz;
	/**
	 * A kapuk sz�m�t ki�r� label
	 */
	Label k;
	/**
	 * Az aszteroida�v, amiben a j�t�k j�tsz�dik
	 */
	Aszteroidaov jatekter;
	/**
	 * A j�t�kt�r
	 */
	Jatekter jtk;
	/**
	 * A men�s�v
	 */
	Menusav m;
	/**
	 * Az aszteroida, aminaz aktu�lis telepes van
	 */
	Aszteroida aktualis;
	/**
	 * A j�t�k�llapot
	 */
	JatekAllapot jatek;
	
	/**
	 * Fel�p�ti a men�s�v grafikus kin�zet�t �s visszaadja azt
	 * @param ov : Az aszteroida�v, amiben a j�t�k j�tsz�dik
	 * @param j : A j�t�kt�r
	 * @param menu : A men�s�v
	 * @param ja : A J�t�k �llapot
	 * @return men�s�v grafikus kin�zete
	 */
	public HBox felepit(Aszteroidaov ov, Jatekter j, Menusav menu, JatekAllapot ja) {
		jatek = ja;
		jatekter = ov;
		jtk = j;
		m=menu;
		aktualis= Main.game.GetOv().getAktual().getAszteroida();
		
		HBox root = new HBox(8);
		
		GridPane buttons2 = new GridPane();
		buttons2.setPadding(new Insets(10, 10, 10, 10));
		buttons2.setVgap(5); 
	    buttons2.setHgap(5);
		
		asas  = new Button("Furas");
		asas.setStyle("-fx-background-color: DARKGOLDENROD");
		asas.setTextFill(Color.WHITE);
		asas.setMinSize(70, 30);
		asas.setOnAction(this);
		
		banyasz = new Button("Banyaszas");
		banyasz.setStyle("-fx-background-color: DARKGOLDENROD");
		banyasz.setTextFill(Color.WHITE);
		banyasz.setMinSize(70, 30);
		banyasz.setOnAction(this);
		
		mozog = new Button("Mozgas");
		mozog.setStyle("-fx-background-color: DARKGOLDENROD");
		mozog.setTextFill(Color.WHITE);
		mozog.setMinSize(70, 30);
		mozog.setOnAction(this);
		
		robotep = new Button("Robot");
		robotep.setStyle("-fx-background-color: DARKGOLDENROD");
		robotep.setTextFill(Color.WHITE);
		robotep.setMinSize(70, 30);
		robotep.setOnAction(this);
		
		kapuep = new Button("Kapu");
		kapuep.setStyle("-fx-background-color: DARKGOLDENROD");
		kapuep.setTextFill(Color.WHITE);
		kapuep.setMinSize(70, 30);
		kapuep.setOnAction(this);
		
		bazisep = new Button("Bazis");
		bazisep.setStyle("-fx-background-color: DARKGOLDENROD");
		bazisep.setTextFill(Color.WHITE);
		bazisep.setMinSize(70, 30);
		bazisep.setOnAction(this);
		
		visszatolt = new Button("Visszatolt");
		visszatolt.setStyle("-fx-background-color: DARKGOLDENROD");
		visszatolt.setTextFill(Color.WHITE);
		visszatolt.setMinSize(70, 30);
		visszatolt.setOnAction(this);
		
		ny1 = new Circle(20, Color.DIMGREY);
		ny2 = new Circle(20, Color.LIGHTGREEN);
		ny3 = new Circle(20, Color.LIGHTSKYBLUE);
		ny4 = new Circle(20, Color.BLACK);
		kapu = new Rectangle(20, 50, Color.CORAL);
		
		jatekos=new Label(jatekter.getAktual().getNev());
		jatekos.setStyle("-fx-font-weight: bold");
		jatekos.setMinSize(300, 27);
		jatekos.setTextAlignment(TextAlignment.CENTER);
		jatekos.setAlignment(Pos.CENTER);
		jatekos.setFont(new Font("Cambria", 32));
		jatekos.setTextFill(Color.RED);
		
		ny1sz = new Label("0");
		ny1sz.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold");
		ny1sz.setMinSize(20, 27);
		ny1sz.setTextAlignment(TextAlignment.CENTER);
		ny1sz.setAlignment(Pos.CENTER);
		 
		ny2sz = new Label("0");
		ny2sz.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold");
		ny2sz.setMinSize(20, 27);
		ny2sz.setTextAlignment(TextAlignment.CENTER);
		ny2sz.setAlignment(Pos.CENTER);
		
		ny3sz = new Label("0");
		ny3sz.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold");
		ny3sz.setMinSize(20, 27);
		ny3sz.setTextAlignment(TextAlignment.CENTER);
		ny3sz.setAlignment(Pos.CENTER);
		
		ny4sz = new Label("0");
		ny4sz.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold");
		ny4sz.setMinSize(20, 27);
		ny4sz.setTextAlignment(TextAlignment.CENTER);
		ny4sz.setAlignment(Pos.CENTER);
		
		k = new Label("0");
		k.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold");
		k.setMinSize(20, 27);
		k.setTextAlignment(TextAlignment.CENTER);
		k.setAlignment(Pos.CENTER);
		
		buttons2.add(mozog, 0, 0);
		buttons2.add(banyasz, 1, 0);
		buttons2.add(asas, 0, 1);
		buttons2.add(visszatolt, 1, 1);
		buttons2.setAlignment(Pos.CENTER);
		root.getChildren().addAll(jatekos, buttons2, robotep, kapuep, bazisep, ny1, ny1sz, ny2, ny2sz, ny3, ny3sz, ny4, ny4sz, kapu, k);
		
		root.setAlignment(Pos.CENTER);
		root.setStyle("-fx-background-color: GOLDENROD");
		
		return root;
	}

	/**
	 * Ez a f�ggv�ny kezeli a m�velets�von tal�lhat� gombok lenyom�s�t
	 */
	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == asas) {
			jatekter.getAktual().Furas();
			if(jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()) != null)
				jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()).frissit(false);
		}
		else if(event.getSource() == banyasz) {
			jatekter.getAktual().Banyaszat();
			setLabels(jatekter.getAktual());
			if(jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()) != null)
				jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()).frissit(false);
		}
		else if(event.getSource() == mozog) {
			if(jtk.getKivalasztott()!=null) {
				if(jatekter.getAktual().getAszteroida().getSzomszedok().contains(jtk.getKivalasztott().getAszteroida())){
					jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()).setAktual(false);
					jatekter.getAktual().Mozgas(jtk.getKivalasztott().getAszteroida());	
				}
			}
			
		}
		else if(event.getSource() == robotep) {
			jatekter.getAktual().RobotEpit();	
		}
		else if(event.getSource() == kapuep) {
			jatekter.getAktual().KapuEpit();
		}
		else if(event.getSource() == bazisep) {
			String s = Main.game.GetOv().getAktual().getAszteroida().BazisEpit();
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText(s);
			a.show();
		}
		else if(event.getSource() == visszatolt) {
			jatekter.getAktual().Visszatolt();
			setLabels(jatekter.getAktual());
			if(jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()) != null)
				jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()).frissit(true);
		}
		jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()).setAktual(false);
		jatek.ujJatekosJon();
		m.Update(jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()), false);
		jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()).setAktual(true);
		setLabels(jatekter.getAktual());
	}
	
	/**
	 * Be�ll�tja az egyes nyersanyagokat jelk�pez� labeleket �s a hozz�juk tartoz� mennyis�geket.
	 * @param t : A telepes, akinek a t�rol�j�b�l lek�rdezik a nyersanyagok mennyis�g�t
	 */
	void setLabels(Telepes t) {
		Uran u = new Uran();
		Vas v = new Vas();
		Szen sz = new Szen();
		Vizjeg vj = new Vizjeg();
		int u_sz = 0;
		int v_sz = 0;
		int sz_sz = 0;
		int vj_sz = 0;
		for(int i = 0; i < t.NyersanyagokSize(); i++) {
			if(u.Kompatibilis(t.getNy(i)))
				u_sz++;
			else if(v.Kompatibilis(t.getNy(i)))
				v_sz++;
			else if(sz.Kompatibilis(t.getNy(i)))
				sz_sz++;
			else if(vj.Kompatibilis(t.getNy(i)))
				vj_sz++;
		}
		String u_s = "" + u_sz;
		String v_s = "" + v_sz;
		String sz_s = "" + sz_sz;
		String vj_s = "" + vj_sz;
		ny1sz.setText(v_s);
		ny2sz.setText(u_s);
		ny3sz.setText(vj_s);
		ny4sz.setText(sz_s);
		String k_s = "" + t.KapukSize();
		k.setText(k_s);
		jatekos.setText(jatekter.getAktual().getNev());
	}
}
