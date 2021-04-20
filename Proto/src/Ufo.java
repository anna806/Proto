
import java.util.Random;

public class Ufo extends Entitas implements Intelligencia {
	
	/**
	 * Az id egyediségét biztosító számláló
	 */
	private static int count=0;
	/**
	 * Az azonosításhoz szükséges ID
	 */
	//private String ID;
	
	/**
	 * Ufó konstruktora
	 */
	Ufo(){
		super();
		ID="u0"+count++;
	}
	
	/**
	 *véletlenszerû mozgást valósít meg az aszteroida egy szomszédjára
	 */
	public void RandomMozgas() {
		aszteroida.Ledob(this);
		if(aszteroida.SzomszedotAd() != null)
			aszteroida.SzomszedotAd().Befogad(this);
		else
			Halal();
	}
	
	/**
	 *Az ufo halálát megvalósító fv, az aszteroidaöv ufok listájából kitörli az egyedet
	 */
	public void Halal() {	
		Main.game.GetOv().MinuszUfo(this);
	}
	
	/**
	 *az ufo felrobbanását megvalósító fv, meghívja a Halal() függvényt
	 */
	public void Felrobban() {
		Halal();
	}
	
	/**
	 *az ufo lépése: ugyanolyan valószínûséggel bányászik, mint hogy tovább lép egy random aszteroidára
	 */
	public void Lep() {
		Random rand = new Random();
		int r = rand.nextInt();
		if(r % 2 == 0)
			Banyaszat();
		else
			RandomMozgas();
	}
	
	/**
	 *bányászat: az aktuális aszteroidán megpróbál anyagot kinyerni
	 */
	public void Banyaszat() { 
		Nyersanyag belsoAnyag = aszteroida.AnyagKinyeres();
		aszteroida.AnyagTorol();
	}
	
	/**
	 * A fúráshoz szükséges függvény - az ufó nem tud fúrni
	 */
	public void Furas() {
	}
	
	/**
	 * A bázisépítéshez szükséges függvény - az ufó nem tud bázist építeni
	 */
	public boolean BazisEpit(Utmutato u) { return false; }

	/**
	 * Kiírja az ufó tulajdonságait a kimenetre
	 */
	public void kiir() {
		System.out.println(this + ": " + ID);
		System.out.println("Aszteroida: " + aszteroida.getID());
	}
	
	/**
	 * Visszaadja egy Stringben az entitás típusát
	 * @return az entitás típusa stringben
	 */
	public String toString() {
		return "Ufo";
	}

	/**
	 * Az építés során  visszaadja, hogy teljesítve van e az útmutató.
	 * @param bazis : Az útmutató az építéshez
	 * @return a robot nem tud építeni, így nemmel tér vissza
	 */
	public boolean AnyagokTorol(Utmutato bazis) { return false; }
	
}
