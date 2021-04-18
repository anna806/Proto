import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aszteroida extends Szomszed{
	/**
	 * megvalsósítja az aszteroidákat
	 */
	private static final long serialVersionUID = 3753080766421296425L;
	private int kopenyVastagsag;
	private boolean napkozel;
	private List<Entitas> entitasok = new ArrayList<>();
	private List<Szomszed> szomszedok = new ArrayList<>();
	private Nyersanyag belsoAnyag;
	private static int count=0;
	private String ID;
	
	/**
	 * aszteroida konstruktora, létrehozza az entitasok, szomszedok listákat, 
	 * a napközel alapértelmezett értéke a false, valamint beállítja az egyedi ID-t
	 */
	public Aszteroida() {
		entitasok = new ArrayList<Entitas>();
		szomszedok = new ArrayList<Szomszed>();
		napkozel = false;
		ID="a0"+count++;
	}
	
	
	
	/**
	 * beállítja a napkozel változó értékét
	 * @param b ez a logikai érték lesza változó értéke
	 */
	public void setNapkozel(boolean b) {
		napkozel=b;
	}
	
	
	/**Az aszteroida belsõ anyagának eltávolítása
	 * @return visszaadja az aszteroidában lévõ nyersanyagot
	 */
	public Nyersanyag AnyagKinyeres() {
		belsoAnyag = null;
		return belsoAnyag;
	}
	
	
	/**a kapott entitást eltávolítja az entitások listából
	 * @param a : az az entitás, amelyet el kell távolítani
 	 */
	public void Ledob(Entitas a) {
		entitasok.remove(a);
	}
	
	/**a kapott entitást felveszi az entitások listába
	 * @param a : az az entitás, amelyet fel kell venni
	 */
	public void Befogad(Entitas a) {
		entitasok.add(a);
		a.SetAszteroida(this);
	}
	
	/**
	 *a napvihar abban az esetben, ha az aszteroida nem üreges, megöli az aszteroidán tartózkodó 
	 *entitásokat. meghívja a szomszédok megkergül függvényét, ami azért felel, hogy az aszteroidán
	 * lévõ kapuk meg tudjanak kergülni
	 */
	public void Napvihar() {
		if(belsoAnyag != null) {
			for(Entitas e: entitasok) {
				e.Halal();
			}	
		}
		for(Szomszed sz: szomszedok) {
			sz.MegKergul();
		}
	}
	
	public void MegKergul() {
	}
	
	/**
	 * A paraméterként kapott szomszédot törli a szomszéd listából
	 * @param a: a törlendõ szomszéd
	 */
	public void SzomszedTorol(Szomszed a) {
		szomszedok.remove(a);
	}
	
	/**
	 * bázisépítés kezdeményezése, a függvény ellenõrzi, hogy megvannak-e a megfelelõ anyagok.
	 * Ha megvannak, a játék végét true értékkel hívja- ilyenkor a játékosok nyernek
	 */
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
	
	/**
	 * csökkenti az aszteroida kérgét 1-gyel. ha a kéreg 0-ra csökkent, és az 
	 * aszteroida napközelben van, meghívja a belsõ anyag napközel függvényét
	 */
	public void KeregCsokken() {
		kopenyVastagsag--;
		if (kopenyVastagsag == 0 && belsoAnyag != null) {
			if (napkozel) {
				this.belsoAnyag.Napkozel(this);
			}
		}
	}
	
	/**
	 * Az aszterida felrobban, ennek során meghívja a rajta tartózkodó entitások felrobban függvényét, 
	 * és eltávolítja az aszteroida belsõ anyagát. ezután az aszteroida eltávolításra kerül az összes 
	 * szomszéd szomszédai közül.
	 */
	public void Robban() {   
		for(Entitas e: entitasok) {
			e.Felrobban();
		}
		AnyagTorol();
		
		for(Szomszed e: szomszedok) {
			e.SzomszedTorol(this);
		}
	}
	
	/**
	 * @return visszaad egy szomszédot véletlenszerûen a szomszédok közül
	 */
	public Szomszed SzomszedotAd() {
		Random rand = new Random();
		int i = rand.nextInt(szomszedok.size());
		return szomszedok.get(i);
	}
	
	/**
	 * Raktározó függvény, amely a
	 * @param a kapott nyersanyagot
	 * @param t kapott telepestõl
	 * elveszi és a belsejében elhelyezi abban az esetben, ha az aszteroida ki van fúrva és üreges
	 * ha az aszteroida épp napközelben van, a nyersanyag Napkozel függvénye hívódik meg
	 */
	public void Raktaroz(Nyersanyag a, Telepes t) {
		if(belsoAnyag == null && kopenyVastagsag == 0) {
			t.RemoveNyersanyag(a);
			this.belsoAnyag = a;
		}
		if(napkozel) {
			belsoAnyag.Napkozel(this);
		}
	}
	
	/**
	 * kitörli az aszteroida belsõ anyagát, ha a kéregvastagság 0
	 */
	public void AnyagTorol() {
		if (this.kopenyVastagsag==0)
		belsoAnyag = null;
	}
	
	/**
	 * Napvihart indít az adott aszteroidán és azok szomszédain is
	 */
	public void StartNapvihar() {
		this.Napvihar();
		for(Szomszed sz: szomszedok) {
			sz.Napvihar();
		}
	}	
	
	/**
	 * a kapott teleportkaput felveszi az aszteroida szomszédai közé
	 *@param k a teleportkapu amelyet fel kell venni
	 */
	public void KapuBefogad(Teleportkapu k) {
		szomszedok.add(k);
	}
	
	/**
	 * A paraméterként kapott nyersanyagot beállítja az aszteroida magjaként
	 * @param ny a nyersanyag amelyet be kell állítani
	 */
	public void setNyersanyag(Nyersanyag ny) {
		belsoAnyag = ny;
	}
	
	public void setID(String id) {
		ID = id;
	}
	public String getID() {
		return ID;
	}
	
	/**
	 * @return visszatérési értékében megadja az aszteroida köpenyének vastagságát
	 */
	public int getKopenyVastagsag() {
		return kopenyVastagsag;
	}
	
	/**
	 * a kapott számot beállítja az aszteroida köpenyvastagságának
	 * @param kv a szám amilyen vastagságú legyen a köpeny
	 */
	public void setKopenyVastagsag(int kv) {
		kopenyVastagsag = kv;
	}
	
	/**
	 * a paraméterben kapott szomszédot beteszi a szomszédok listába
	 * @param sz a kapott szomszéd
	 */
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
