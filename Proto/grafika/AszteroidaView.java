package grafika;

import proto.*;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Line;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AszteroidaView extends SzomszedView {
	private Aszteroida data;
	public Circle kor;
	public int x;
	public int y;
	public NyersanyagView anyag;
	private List<KapuView> kapunezet;
	public List<Line> vonalak= new ArrayList<Line>();
	public List<SzomszedView> szomszedok;
	public Jatekter jt;
	private boolean aktualis;
	private boolean valasztott;
	
	Pane pane;
	
	/**
	 * Az AszteroidaView konstruktora
	 * @param a: az aszteroida, amelynek a nézetét kezeljük az osztályban
	 * @param kx: középpont x koordinátája
	 * @param ky: középpont y koordinátája
	 * @param p: tároló
	 * @param j: aszteroidaöv megjelenítése a játékban
	 */
	public AszteroidaView(Aszteroida a, int kx, int ky, Pane p, Jatekter j) {
		pane = p; 
		data=a;
		x=kx;
		y=ky;
		jt=j;
		
		kor= new Circle();
		kor.setCenterX(x);
		kor.setCenterY(y);
		kor.setRadius(30.0f);
		kor.setFill(Color.CORNFLOWERBLUE);
		if(data.getBelsoAnyag() != null) {
			if(data.getBelsoAnyag().Kompatibilis(new Uran())) {
				anyag = new UranView(data.getBelsoAnyag(), x, y);
			}
			else if(data.getBelsoAnyag().Kompatibilis(new Szen())) {
				anyag = new SzenView(data.getBelsoAnyag(), x, y);
			}
			else if(data.getBelsoAnyag().Kompatibilis(new Vas())) {
				anyag = new VasView(data.getBelsoAnyag(), x, y);
			}
			else if(data.getBelsoAnyag().Kompatibilis(new Vizjeg())) {
				anyag = new VizjegView(data.getBelsoAnyag(), x, y);
			}				
		}
		else {
			anyag = new NyersanyagView(data.getBelsoAnyag(), x, y);
			anyag.ures();
		}
		szomszedok=new ArrayList<SzomszedView>();
		for(int i = 0; i < data.SzomszedokSize(); i++) {
		}
		
		kapunezet = new ArrayList<KapuView>();
		List<Teleportkapu> kapulista=new ArrayList<Teleportkapu>();
		for(int i=0; i<Main.game.GetOv().GetKapukSize(); i++) {
			if(Main.game.GetOv().GetKapu(i).getAszter().equals(data)) {
				kapulista.add(Main.game.GetOv().GetKapu(i));
				
			}
		}
		if(kapulista.size()>0) {
			for(int i=0; i<kapulista.size(); i++) {
			KapuView kv= new KapuView(kapulista.get(i), x+40, y-30);
			kapunezet.add(kv);
			}
		}
	}
	
	/**
	 * SzomszédView hozzácsatolása az AszteroidaViewhoz
	 * @param sz: a szomszéd nézete
	 */
	public void addSzomszed(SzomszedView sz) {
		szomszedok.add(sz);
	}
	
	/**
	 * Szomszédsági él kirajzolása a paraméterként megkapott aszteroidához
	 * @param ref: a szomszédos aszteroida
	 */
	public void SzomszedMutat(AszteroidaView ref) {
		Line line = new Line();
		if(kor.getCenterX()<ref.kor.getCenterX()) {
			
			line.setStartX(kor.getCenterX()+30);
			line.setStartY(kor.getCenterY());
			line.setEndX(ref.kor.getCenterX()-30);
			line.setEndY(ref.kor.getCenterY());
		}
		
		else {
			line.setStartX(kor.getCenterX()-30);
			line.setStartY(kor.getCenterY());
			line.setEndX(ref.kor.getCenterX()+30);
			line.setEndY(ref.kor.getCenterY());
		}
		line.setStroke(Color.RED);
		vonalak.add(line);
		pane.getChildren().add(line);
	}
	
	/**
	 * Ezt az AszteroidaView-t beállítani aktuálisnak, 
	 * tehát a piros körvonalat adni neki.
	 * @param akt: igaz, ha ez az aktuális
	 */
	public void setAktual(boolean akt) {
		if (akt) {
			kor.setStroke(Color.RED);
			aktualis=akt;
			for(SzomszedView szv : szomszedok) {
				szv.SzomszedMutat(this);
			}
		}
		else { 
			kor.setStroke(Color.TRANSPARENT);
			aktualis=akt;
			for(SzomszedView szv : szomszedok) {
				for(int i = 0; i < szv.getVonalak().size(); i++)
					pane.getChildren().remove(szv.getVonalak().get(i));
				szv.getVonalak().clear();
			}
		}
		jt.Update(this, akt);
	}
	
	/**
	 * Ezt az AszteroidaView-t beállítani aktuálisnak, 
	 * tehát a zöld körvonalat adni neki.
	 * @param val: igaz, ha ez a választott
	 */
	public void setValaszt(boolean val) {
		if(val) {
			kor.setStroke(Color.GREEN);
			valasztott=val;
		}
		else {
			kor.setStroke(Color.TRANSPARENT);
			valasztott=val;
		}
	}
	
	/**
	 * Aszteroida nézet és kapuk hozzáadása a bázishoz
	 */
	public void felepit() {
		pane.getChildren().add(kor);
		if(anyag!=null && data.getKopenyVastagsag() == 0) {
			anyag.feltesz(pane);
		}
		if(kapunezet.size()>0) {
			for (KapuView nezet : kapunezet){
				nezet.feltesz(pane);
			}
		}
	}
	
	/**
	 * Adatok frissítése.
	 * @param visszatolt: ha visszatöltés mûvelet eredményeként hívódik a függvény
	 */
	public void frissit(boolean visszatolt) {
		if(data.getBelsoAnyag() != null && data.getKopenyVastagsag() == 0) {
			if(visszatolt) {
				anyag.setData(data.getBelsoAnyag());
				anyag.kitolt();
			}
			else
				anyag.feltesz(pane);
		}
		else if(data.getBelsoAnyag() == null && data.getKopenyVastagsag() == 0 && anyag.getData() == null) {
			anyag.ures();
			anyag.feltesz(pane);
		}
		else if(data.getBelsoAnyag() == null) {
			anyag.ures();
		}
	}
	
	/**
	 * @return visszaadja az Aszteroida osztályú tagot
	 */
	public Aszteroida getAszteroida() {
		return data;
	}
	
	/**
	 * @return visszaadja a szomszédsági éleket tároló listát
	 */
	public List<Line> getVonalak(){
		return vonalak;
	}
}
	
