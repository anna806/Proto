package grafika;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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

	public void felepit(Aszteroidaov ov, Jatekter j) {
		jatekter = ov;
		jtk = j;
		asas.setOnAction(this);
		banyasz.setOnAction(this);
		mozog.setOnAction(this);
		robotep.setOnAction(this);
		kapuep.setOnAction(this);
		bazisep.setOnAction(this);
		visszatolt.setOnAction(this);
		
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
