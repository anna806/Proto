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
import javafx.scene.text.TextAlignment;
import proto.Aszteroidaov;
import proto.Nyersanyag;
import proto.Szen;
import proto.Telepes;
import proto.Uran;
import proto.Vas;
import proto.Vizjeg;


public class Muveletsav extends Pane implements EventHandler<ActionEvent>{
	
	Nyersanyag valasztott;
	Button asas;
	Button banyasz;
	Button mozog;
	Button robotep;
	Button kapuep;
	Button bazisep;
	Button visszatolt;
	Circle ny1;		//vas
	Circle ny2;		//uran
	Circle ny3;		//vizjeg
	Circle ny4;		//szen
	Rectangle kapu;
	Label ny1sz;	//vas
	Label ny2sz;	//uran
	Label ny3sz;	//vizjeg
	Label ny4sz;	//szen
	Label k;
	Aszteroidaov jatekter;
	Jatekter jtk;

	
	public HBox felepit(Aszteroidaov ov, Jatekter j) {
		jatekter = ov;
		jtk = j;
		
		HBox root = new HBox(8);
		
		GridPane buttons2 = new GridPane();
		buttons2.setPadding(new Insets(10, 10, 10, 10));
		buttons2.setVgap(5); 
	    buttons2.setHgap(5);
		
		asas  = new Button("Furas");
		asas.setStyle("-fx-background-color: DARKGOLDENROD");
		asas.setTextFill(Color.WHITE);
		asas.setMinSize(70, 45);
		asas.setOnAction(this);
		
		banyasz = new Button("Banyaszas");
		banyasz.setStyle("-fx-background-color: DARKGOLDENROD");
		banyasz.setTextFill(Color.WHITE);
		banyasz.setMinSize(70, 45);
		banyasz.setOnAction(this);
		
		mozog = new Button("Mozgas");
		mozog.setStyle("-fx-background-color: DARKGOLDENROD");
		mozog.setTextFill(Color.WHITE);
		mozog.setMinSize(70, 45);
		mozog.setOnAction(this);
		
		robotep = new Button("Robot");
		robotep.setStyle("-fx-background-color: DARKGOLDENROD");
		robotep.setTextFill(Color.WHITE);
		robotep.setMinSize(70, 45);
		robotep.setOnAction(this);
		
		kapuep = new Button("Kapu");
		kapuep.setStyle("-fx-background-color: DARKGOLDENROD");
		kapuep.setTextFill(Color.WHITE);
		kapuep.setMinSize(70, 45);
		kapuep.setOnAction(this);
		
		bazisep = new Button("Bazis");
		bazisep.setStyle("-fx-background-color: DARKGOLDENROD");
		bazisep.setTextFill(Color.WHITE);
		bazisep.setMinSize(70, 45);
		bazisep.setOnAction(this);
		
		visszatolt = new Button("Visszatolt");
		visszatolt.setStyle("-fx-background-color: DARKGOLDENROD");
		visszatolt.setTextFill(Color.WHITE);
		visszatolt.setMinSize(70, 45);
		visszatolt.setOnAction(this);
		
		ny1 = new Circle(20, Color.DIMGREY);
		ny2 = new Circle(20, Color.LIGHTGREEN);
		ny3 = new Circle(20, Color.LIGHTSKYBLUE);
		ny4 = new Circle(20, Color.BLACK);
		kapu = new Rectangle(20, 50, Color.CORAL);
		
		ny1sz = new Label("0");
		ny1sz.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold");
		ny1sz.setMinSize(20, 27);
		ny1sz.setTextAlignment(TextAlignment.CENTER);
		
		ny2sz = new Label("0");
		ny2sz.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold");
		ny2sz.setMinSize(20, 27);
		ny2sz.setTextAlignment(TextAlignment.CENTER);
		
		ny3sz = new Label("0");
		ny3sz.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold");
		ny3sz.setMinSize(20, 27);
		ny3sz.setTextAlignment(TextAlignment.CENTER);
		
		ny4sz = new Label("0");
		ny4sz.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold");
		ny4sz.setMinSize(20, 27);
		ny4sz.setTextAlignment(TextAlignment.CENTER);
		
		k = new Label("0");
		k.setStyle("-fx-background-color: WHITE; -fx-font-weight: bold");
		k.setMinSize(20, 27);
		k.setTextAlignment(TextAlignment.CENTER);
		
		buttons2.add(mozog, 0, 0);
		buttons2.add(banyasz, 1, 0);
		buttons2.add(asas, 0, 1);
		buttons2.add(visszatolt, 1, 1);
		buttons2.setAlignment(Pos.CENTER);
		root.getChildren().addAll(buttons2, robotep, kapuep, bazisep, ny1, ny1sz, ny2, ny2sz, ny3, ny3sz, ny4, ny4sz, kapu, k);
		
		root.setAlignment(Pos.CENTER);
		root.setStyle("-fx-background-color: GOLDENROD");
		
		return root;
	}

	@Override
	public void handle(ActionEvent event) {
		if(event.getSource() == asas) {
			jatekter.getAktual().Furas();
			if(jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()) != null)
				jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()).frissit();
		}
		else if(event.getSource() == banyasz) {
			jatekter.getAktual().Banyaszat();
			setLabels(jatekter.getAktual());
			if(jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()) != null)
				jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()).frissit();
		}
		else if(event.getSource() == mozog) {
			jatekter.getAktual().Mozgas(jtk.getKivalasztott().getAszteroida());	//egérkattintással kiválasztott aszteroida
		}
		else if(event.getSource() == robotep) {
			jatekter.getAktual().RobotEpit();	
		}
		else if(event.getSource() == kapuep) {
			jatekter.getAktual().KapuEpit();
		}
		else if(event.getSource() == bazisep) {
			String s = jtk.getKivalasztott().getAszteroida().BazisEpit();
			Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText(s);
			a.show();
			//jatek vege függvénye tud csak arról, hogy nyertek e vagy sem, õ hív grafikus függvényt, hogy csináljon ablakot?
		}
		else if(event.getSource() == visszatolt) {
			jatekter.getAktual().Visszatolt();
			setLabels(jatekter.getAktual());
			if(jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()) != null)
				jtk.getAszteroidaView(jatekter.getAktual().getAszteroida()).frissit();
		}
		jatekter.aktualKesz(); //játékos továbbléptetése a köre után
	}
	
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
	}
}
