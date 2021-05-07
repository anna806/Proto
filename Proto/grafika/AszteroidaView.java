package grafika;

import proto.*;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Line;
import javafx.scene.Group;
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
	public List<Line> vonalak;
	public List<SzomszedView> szomszedok;
	
	Group group;
	
	public AszteroidaView(Aszteroida a, int kx, int ky, Group g) {
		group = g;
		data=a;
		x=kx;
		y=ky;
		
		kor= new Circle();
		kor.setCenterX(x);
		kor.setCenterY(y);
		kor.setRadius(50.0f);
		kor.setFill(Color.CORNFLOWERBLUE);
		if (data.getKopenyVastagsag()==0) {
			anyag=new NyersanyagView(data.getBelsoAnyag(), x, y);
			anyag.kitolt();
			
		}
		szomszedok=new ArrayList<SzomszedView>();
		
		List<Teleportkapu> kapulista=new ArrayList<Teleportkapu>();
		for(int i=0; i<Main.game.GetOv().GetKapukSize(); i++) {
			if(Main.game.GetOv().GetKapu(i).getAszter().equals(data)) {
				kapulista.add(Main.game.GetOv().GetKapu(i));
			}
		}
		if(kapulista.size()>0) {
			for(int i=0; i<kapulista.size(); i++) {
			KapuView kv= new KapuView(kapulista.get(i), x+60, y, group);
			kapunezet.add(kv);
			}
		}
	}
	
	public void addSzomszed(SzomszedView sz) {
		szomszedok.add(sz);
	}
	
	public void SzomszedMutat(AszteroidaView ref) {
		vonalak= new ArrayList<Line>();
		if(kor.getCenterX()<ref.kor.getCenterX()) {
			Line line = new Line();
			line.setStartX(kor.getCenterX()+50);
			line.setStartY(kor.getCenterY());
			line.setEndX(ref.kor.getCenterX()-50);
			line.setEndY(ref.kor.getCenterY());
			vonalak.add(line);
		}
		
		else {
			Line line = new Line();
			line.setStartX(kor.getCenterX()-50);
			line.setStartY(kor.getCenterY());
			line.setEndX(ref.kor.getCenterX()+50);
			line.setEndY(ref.kor.getCenterY());
			vonalak.add(line);
		}
			
	}
	
	public void setAktual(boolean akt) {
		if (akt) {
			kor.setStroke(Color.RED);
			aktualis=akt;
		}
		else {
			kor.setStroke(Color.TRANSPARENT);
			aktualis=akt;
		}
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
		group.getChildren().add(kor);
		if(anyag!=null) {
			anyag.feltesz(group);
		}
		if(kapunezet.size()>0) {
			for (KapuView nezet : kapunezet){
				nezet.feltesz(group);
			}
		}
		if(aktualis) {
			for (Line l : vonalak){
				l.setFill(Color.RED);
				group.getChildren().add(l);
			}
		}
		if(valasztott) {
			for (Line l : vonalak){
				l.setFill(Color.GREEN);
				group.getChildren().add(l);
			}
		}
//		Scene scene= new Scene(group, 110, 110);
//		stage.setScene(scene);
//		stage.show();
	}
	
	public void frissit() {
		if(anyag != null) {
			anyag.feltesz(group);
		}
		else if(anyag == null) {
			anyag.ures();
		}
	}
	
	public Aszteroida getAszteroida() {
		return data;
	}
}
	
