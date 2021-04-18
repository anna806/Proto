import java.util.ArrayList;
import java.util.List;

import szkeleton.game.Main;
import szkeleton.game.Szen;
import szkeleton.game.Uran;
import szkeleton.game.Vas;
import szkeleton.game.Vizjeg;

public class Telepes extends Entitas {
	private List<Teleportkapu> kapuk = new ArrayList<>();
	private List<Nyersanyag> nyersanyagok = new ArrayList<>();
	private static int count=0;
	private String ID;
	
	Telepes(){
		super();
		ID="t0"+count++;
	}
	
	public void Felrobban() {
		for(int i = 0; i < kapuk.size(); i++) {
			kapuk.get(i).Robban();
		}
		nyersanyagok.removeAll(nyersanyagok);
		Halal();
	}
	
	public void Mozgas(Szomszed a) {
		aszteroida.Ledob(this);
		a.Befogad(this);
	}
	
	public void Banyaszat() {
		Nyersanyag belsoAnyag = aszteroida.AnyagKinyeres();
		if(nyersanyagok.size() < 10) 
			belsoAnyag.Betolt(this);
		aszteroida.AnyagTorol();
	}
	
	public void Visszatolt() {
		aszteroida.Raktaroz(nyersanyagok.get(nyersanyagok.size() - 1), this);
	}
	
	public void RobotEpit() {
		List<Nyersanyag> kell = new ArrayList<>();
		kell.add(new Vas());
		kell.add(new Szen());
		kell.add(new Uran());
		boolean done = false;
		Utmutato robot = new Utmutato(kell);
		for(int i = 0; i < nyersanyagok.size(); i++) {
			if(robot.MindMegvan(nyersanyagok.get(i))) {
				done = true;
				break;
			}	
		}
		if(done) {
			Robot r = new Robot();
			r.SetAszteroida(aszteroida);
			aszteroida.Befogad(r);
		}
	}
	
	public void KapuEpit() {
		if(kapuk.size() <= 1) {
			List<Nyersanyag> kell = new ArrayList<>();
			kell.add(new Vas());
			kell.add(new Vas());
			kell.add(new Vizjeg());
			kell.add(new Uran());
			Utmutato tkapu = new Utmutato(kell);
			boolean done = false;
			for(int i = 0; i < nyersanyagok.size(); i++) {
				if(tkapu.MindMegvan(nyersanyagok.get(i))) {
					done = true;
					break;
				}
			}
			if(done) {
				Teleportkapu k1 = new Teleportkapu();
				Teleportkapu k2 = new Teleportkapu();
				k1.setParja(k2);
				k2.setParja(k1);
			}
		}
	}
	
	public void KapuLerak() {
		if(kapuk.size() != 0) {
			aszteroida.addSzomszed(kapuk.get(kapuk.size() - 1));
			kapuk.get(kapuk.size() - 1).setAszter(aszteroida);
			Aszteroida parhelye = kapuk.get(kapuk.size() - 1).ParHelye();
			if(parhelye != null)
				parhelye.addSzomszed(kapuk.get(kapuk.size() - 1).getParja());	
			kapuk.remove(kapuk.size() - 1);
		}
	}
	
	public boolean BazisEpit(Utmutato bazis) {
		boolean kesz = false;
		for(Nyersanyag ny: nyersanyagok) {
			kesz = bazis.MindMegvan(ny);
		}
		return kesz;
	
	}
	
	public void Halal() {
		for(int i = 0; i < kapuk.size(); i++) {
			kapuk.get(i).Robban();
		}
		nyersanyagok.removeAll(nyersanyagok);
	}
	
	public void Lep() {
	}
	
	public void Furas() {
		if(aszteroida.getKopenyVastagsag() > 0)
			aszteroida.KeregCsokken();
	}
	
	public void AddNyersanyag(Nyersanyag ny) {
		nyersanyagok.add(ny);
	}
	
	public void RemoveNyersanyag(Nyersanyag ny) {
		nyersanyagok.remove(ny);
	}
	
	public void AddKapu(Teleportkapu k) {
		kapuk.add(k);
	}
	
	public Nyersanyag getNyersanyag(String ID) {
		for(Nyersanyag ny : nyersanyagok) {
			if(ny.getID().equals(ID))
				return ny;
		}
		return null;
	}
	public String getID(String id) {
		return ID;
	}

	public int NyersanyagokSize() {
		return nyersanyagok.size();
	}

	public String getNyersanyagok(int i) {
		return nyersanyagok.get(i).toString();
	}

	
	public int KapukSize() {
		return kapuk.size();
	}

	public String getKapuk(int i) {
		return kapuk.get(i).getID();
	}
}
