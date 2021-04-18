import java.util.ArrayList;
import java.util.List;

public class Telepes extends Entitas {
	/**
	 * A telepes inventoryjában található kapuk
	 */
	private List<Teleportkapu> kapuk = new ArrayList<>();
	/**
	 * A telepes inventoryjában található nyersanyagok
	 */
	private List<Nyersanyag> nyersanyagok = new ArrayList<>();
	/**
	 * Az id egyediségét biztosító számláló
	 */
	private static int count=0;
	/**
	 * A telepest azonosító ID
	 */
	private String ID;
	
	/**
	 * A telepes konstruktora
	 */
	Telepes(){
		super();
		ID="t0"+count++;
	}
	
	/**
	 * A telepes felrobbanásáért felelős függvény. Megöli a telepest és ha vannak nála kapuk, azokat is felrobbantja.
	 */
	public void Felrobban() {
		for(int i = 0; i < kapuk.size(); i++) {
			kapuk.get(i).Robban();
		}
		nyersanyagok.removeAll(nyersanyagok);
		Halal();
	}
	
	/**
	 * A Telepes mozgatásáért felelős függvény. 
	 * @param Az a szomszéd a paraméter, amelyre az Telepes mozogni fog.
	 */
	public void Mozgas(Szomszed a) {
		aszteroida.Ledob(this);
		a.Befogad(this);
	}
	
	/**
     * A bányászásért felelős függvény. Kinyeri az aszteroidából a nyersanyagot. Ha elfér az inventoryjában,
     * akkor belerakja, ha nem, kitörli.
     */
	public void Banyaszat() {
		Nyersanyag belsoAnyag = aszteroida.AnyagKinyeres();
		if(nyersanyagok.size() < 10) 
			belsoAnyag.Betolt(this);
		aszteroida.AnyagTorol();
	}
	
	/**
     *  Visszatölti a telepes inventoryjában található utolsó anyagot az aszteroidába
     */
	public void Visszatolt() {
		aszteroida.Raktaroz(nyersanyagok.get(nyersanyagok.size() - 1), this);
	}
	
	/**
	 * A robot megépítésére szolgáló függvény. A robotot a Telepes építi, ha minden nyersanyag nála van, ami kell.
	 */
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
	
	/**
	 * A Telepes létrehoz egy Útmutatót, ami alapján megépítheti a Teleportkapu-párt. 
	 * Az Útmutatónak egyesével átadja a nála lévő nyersanyagokat, ami leellenőrzi, hogy van-e egyezés.
	 * Ha megvan az összes nyersanyag, akkor megépíti a telepes a teleportkapu-párt, és elraktározza.
	 * Ezen kívül beállítja, hogy a két teleportkapu egymás párjai.	
	 */
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
	
	/**
	 * A telepes lerakja a nála lévő Teleportkapuk egyikét arra az aszteroidára,
	 * amin éppen tartózkodik, és beállítja a kapuk szükséges tagváltozóit.
	 */
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
	
	/**
	 * A telepes az Útmutatónak egyesével átadja a nála lévő nyersanyagokat, ami leellenőrzi, hogy van-e egyezés.
	 *@param bazis a bázisépítéshez tartozó útmutató.
	 *@return Egy bool változó, ami megmondja, hogy a bázis megépült-e.
	 */
	public boolean BazisEpit(Utmutato bazis) {
		boolean kesz = false;
		for(Nyersanyag ny: nyersanyagok) {
			kesz = bazis.MindMegvan(ny);
		}
		return kesz;
	
	}
	
	/**
	 * A Telepes halálát intéző függvény.
	 */
	public void Halal() {
		for(int i = 0; i < kapuk.size(); i++) {
			kapuk.get(i).Robban();
		}
		nyersanyagok.removeAll(nyersanyagok);
	}
	
	
	public void Lep() {
	}
	
	/**
	 * Amin a telepes áll aszteroida, köpenyének fúrását végzõ függvény.
	 * Meghívja az Aszteroida kéregcsökkentő függvényét.
	 */
	public void Furas() {
		if(aszteroida.getKopenyVastagsag() > 0)
			aszteroida.KeregCsokken();
	}
	
	/**
	 * Hozzáad egy nyersanyagot a telepes inventory-jához.
	 * @param ny : a hozzáadandó nyersanyag
	 */
	public void AddNyersanyag(Nyersanyag ny) {
		nyersanyagok.add(ny);
	}
	
	/**
	 * Kitöröl egy nyersanyagot a telepes inventory-jából.
	 * @param ny : az eltávolítandó nyersanyag
	 */
	public void RemoveNyersanyag(Nyersanyag ny) {
		nyersanyagok.remove(ny);
	}
	
	/**
	 * Hozzáad egy teleportkaput a telepes inventory-jához.
	 * @param k : a hozzáadandó kapu
	 */
	public void AddKapu(Teleportkapu k) {
		kapuk.add(k);
	}
	
	/**
	 * Visszaad egy kért nyersanyagot ID alapján
	 * @param ID : a szükséges nyersanyag ID-ja
	 * @return visszaadja a kért nyersanyagot, ha bennne van az inventoryban, egyébként null-t ad vissza
	 */
	public Nyersanyag getNyersanyag(String ID) {
		for(Nyersanyag ny : nyersanyagok) {
			if(ny.getID().equals(ID))
				return ny;
		}
		return null;
	}
	
	/**
	 * Lejkérdezi a telepes ID-jét
	 * @return a telepes ID-je
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Visszaadja a telepesnél lévő nyersanyagok számát
	 * @return A nyersanyagok tömb mérete
	 */
	public int NyersanyagokSize() {
		return nyersanyagok.size();
	}
	
	/**
	 * Kiiratja az i-edik nyersanyag leírását
	 * @param i : index
	 * @return a nyersanyag leírása
	 */
	public String getNyersanyagok(int i) {
		return nyersanyagok.get(i).toString();
	}

	/**
	 * Visszaadja a telepesnél lévő kapuk számát
	 * @return A kapuk tömb mérete
	 */
	public int KapukSize() {
		return kapuk.size();
	}

	/**
	 * Kiiratja az i-edik kapu ID-ját
	 * @param i : index
	 * @return a kapu ID-ja
	 */
	public String getKapuk(int i) {
		return kapuk.get(i).getID();
	}
}