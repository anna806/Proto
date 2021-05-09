package grafika;

import proto.*;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Line;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AszteroidaView extends SzomszedView {
	private Aszteroida data;
	public Circle kor;
	public int x;
	public int y;
	public NyersanyagView anyag;
	private boolean aktualis;
	private boolean valasztott; 
	private List<KapuView> kapunezet;
	public List<Line> vonalak= new ArrayList<Line>();
	public List<SzomszedView> szomszedok;
	public Jatekter jt;
	
	Pane pane;
	
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
		/*if (data.getKopenyVastagsag()==0) {
			anyag=new NyersanyagView(data.getBelsoAnyag(), x, y);
			anyag.kitolt();
			
		}*/
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
			KapuView kv= new KapuView(kapulista.get(i), x+60, y);
			kapunezet.add(kv);
			}
		}
	}
	
	public void addSzomszed(SzomszedView sz) {
		szomszedok.add(sz);
	}
	
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
	
	public void setAktual(boolean akt) {
		if (akt) {
			kor.setStroke(Color.RED);
			aktualis=akt;
			for(SzomszedView szv : szomszedok) {
				System.out.println("setaktual");
				szv.getVonalak().clear();
				szv.SzomszedMutat(this);
			}
		}
		else { 
			kor.setStroke(Color.TRANSPARENT);
			aktualis=akt;
			for(SzomszedView szv : szomszedok) {
				for(int i = 0; i < szv.getVonalak().size(); i++)
					pane.getChildren().remove(szv.getVonalak().get(i));
			}
		}
		jt.Update(this, akt);
	}
	
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
	
	public void felepit() {
//		Group group= new Group();
		pane.getChildren().add(kor);
		if(anyag!=null && data.getKopenyVastagsag() == 0) {
			anyag.feltesz(pane);
		}
		if(kapunezet.size()>0) {
			for (KapuView nezet : kapunezet){
				nezet.feltesz(pane);
			}
		}
		
		
//		Scene scene= new Scene(group, 110, 110);
//		stage.setScene(scene);
//		stage.show();
	}
	
	public void frissit(boolean visszatolt) {
		if(data.getBelsoAnyag() != null && data.getKopenyVastagsag() == 0) {
			if(visszatolt)
				anyag.kitolt();
			else
				anyag.feltesz(pane);
		}
		else if(data.getBelsoAnyag() == null) {
			anyag.ures();
		}
	}
	
	public Aszteroida getAszteroida() {
		return data;
	}
	
	public List<Line> getVonalak(){
		return vonalak;
	}
}
	
