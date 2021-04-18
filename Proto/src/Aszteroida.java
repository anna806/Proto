import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aszteroida extends Szomszed{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3753080766421296425L;
	private int kopenyVastagsag;
	private boolean napkozel;
	private List<Entitas> entitasok = new ArrayList<>();
	private List<Szomszed> szomszedok = new ArrayList<>();
	private Nyersanyag belsoAnyag;
	private static int count=0;
	private String ID;
	
	public Aszteroida() {
		entitasok = new ArrayList<Entitas>();
		szomszedok = new ArrayList<Szomszed>();
		napkozel = false;
		ID="a0"+count++;
	}
	
	List<Szomszed> getSzomszedok(){
		return szomszedok;
	}
	
	public void setNapkozel(boolean b) {
		napkozel=b;
	}
	
	
	public Nyersanyag AnyagKinyeres() {
		belsoAnyag = null;
		return belsoAnyag;
	}
	
	//ez így helyes?
	public void Ledob(Entitas a) {
		entitasok.remove(a);
	}
	
	public void Befogad(Entitas a) {
		entitasok.add(a);
	}
	
	public void Napvihar() {
		if(belsoAnyag != null) {
			for(Entitas e: entitasok) {
				e.Halal();
			}
		}
		//kapukon?
		//nekik már nem kéne több szomszédot hívni
	}
	
	public void SzomszedTorol(Szomszed a) {
		szomszedok.remove(a);
	}
	
	public void BazisEpit() {
		List<Nyersanyag> kell = new ArrayList<Nyersanyag>();
			
		kell.add(new Uran());
		kell.add(new Uran());
		kell.add(new Uran());
		
		kell.add(new Vas());
		kell.add(new Vas());
		kell.add(new Vas());
		
		kell.add(new Vizjeg());
		kell.add(new Vizjeg());
		kell.add(new Vizjeg());
		
		kell.add(new Szen());
		kell.add(new Szen());
		kell.add(new Szen());
		
		Utmutato bazis = new Utmutato(kell);
		boolean bazisepitheto = false;
		for (int j = 0; j < entitasok.size(); j++) {
			bazisepitheto = entitasok.get(j).BazisEpit(bazis); //!!!!!!!!!!!!!!!!! útmutató
		}
		if(bazisepitheto) Main.game.Vege(bazisepitheto); 
		
	}
	
	public void KeregCsokken() {
		kopenyVastagsag--;
		if (kopenyVastagsag == 0 && belsoAnyag != null) {
			if (napkozel) {
				this.belsoAnyag.Napkozel(this);
			}
		}
	}
	
	public void Robban() {   
		for(Entitas e: entitasok) {
			e.Felrobban();
		}
		AnyagTorol();
	}
	
	public Szomszed SzomszedotAd() {
		Random rand = new Random();
		int i = rand.nextInt(szomszedok.size());
		return szomszedok.get(i);
	}
	
	public void Raktaroz(Nyersanyag a, Telepes t) {
		if(belsoAnyag == null && kopenyVastagsag == 0) {
			t.RemoveNyersanyag(a);
			this.belsoAnyag = a;
		}
		if(napkozel) {
			belsoAnyag.Napkozel(this);
		}
	}
	
	public void AnyagTorol() {
		belsoAnyag = null;
	}
	
	public void StartNapvihar() {
		this.Napvihar();
		for(Szomszed sz: szomszedok) {
			sz.Napvihar();
		}
	}	
	
	public void KapuBefogad(Teleportkapu k) {
		szomszedok.add(k);
	}
	
	public void setNyersanyag(Nyersanyag ny) {
		belsoAnyag = ny;
	}
	
	public void setID(String id) {
		ID = id;
	}
	public String getID() {
		return ID;
	}
	
	public int getKopenyVastagsag() {
		return kopenyVastagsag;
	}
	
	public void setKopenyVastagsag(int kv) {
		kopenyVastagsag = kv;
	}
	
	public void addSzomszed(Szomszed sz) {
		szomszedok.add(sz);
	}

	public String getNapkozel() {
		if(napkozel) {
			return "true";
		} else {
			return "false";
		}
	}

	public String getBelsoAnyagString() {
		if(belsoAnyag == null) {
			return "Ureges";
		} else {
			return belsoAnyag.toString();
		}
	}
	public Nyersanyag getBelsoAnyag() {
		return belsoAnyag;
	}

	public int EntitasokSize() {
		return entitasok.size();
	}

	public String getEntitas(int i) {
		return entitasok.get(i).getID();
	}

	public int SzomszedokSize() {
		return szomszedok.size();
	}

	public String getSzomszed(int i) {
		return szomszedok.get(i).getID();
	}
	
}
