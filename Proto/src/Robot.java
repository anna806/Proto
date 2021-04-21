
import java.util.Random;


public class Robot extends Entitas implements Intelligencia {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7370920782066937702L;
	/**
	 * azt számolja, hogy hányszor példányosították az osztályt
	 */
	private static int count=1;
	
	/**
	 * A Robot osztály konstruktora, meghívja az õsosztály konstruktorát
	 * és beállítja a robot egyedi azonosítóját 
	 */
	Robot(){
		super();
		ID="r0"+count++;
	}
	
	/**
	 *Lekér az aktuális aszteroidától egy véletlenszerû szomszédot,
	 *amire átmozog
	 */
	public void RandomMozgas() {
		aszteroida.Ledob(this);
		Szomszed sz = aszteroida.SzomszedotAd();
		if(sz == null) {
			this.Halal();
		} else {
			sz.Befogad(this);
		}
		
	}
	
	/**
	 *Felrobbanás során átkerül egy véletlenszerû aszteroidára
	 *a RandomMozgas() függvény segítségével
	 */
	public void Felrobban() {
		RandomMozgas();
	}
	
	/**
	 *Eltávolítja magát az aktuális aszteroidáról, és kitörli magát
	 *az Aszteroidaöv robotok listájából
	 */
	public void Halal() {
		aszteroida.Ledob(this);
		Main.game.GetOv().MinuszRobot(this);
	}
	
	/**
	 *Véletlenszerûen választ, hogy az adott körben mozogni vagy aszteroidát fúrni fog a Robot
	 */
	public void Lep() {
		Random rand = new Random();
		int r = rand.nextInt();
		if(r % 2 == 0)
			Furas();
		else
			RandomMozgas();
	}
	
	/**
	 *Ha az aszteroidának, amin áll a Robot nem 0 a köpenyvastagsága,
	 *akkor megfúrja a köpenyét. 
	 */
	public void Furas() {
		if(aszteroida.getKopenyVastagsag() > 0)
			aszteroida.KeregCsokken();
	}
	
	/**
	 *Változtatást nem végrehajtó, logikai hamissal visszatérõ függvény,
	 *mert a Robot nem tud részt venni a Bázisépítésben
	 */
	public boolean BazisEpit(Utmutato u) { return false; }

	/**
	 *Üres törzsû függvény, mert a Robot nem tud nyersanyagot bányászni
	 */
	public void Banyaszat() {}

	/**
	 *Kiírja a konzolra a Robot különbözõ tulajdonságait
	 */
	public void kiir() {
		System.out.println(this + ": " + ID);
		System.out.println("Aszteroida: " + aszteroida.getID());
	}
	
	/**
	 * Kiírja az entitás fõ tulajdonságát
	 * @return az entitás típusa
	 */
	public String toString() {
		return "Robot";
	}
	
	/**
	 * Az építés során  visszaadja, hogy teljesítve van e az útmutató.
	 * @param bazis : Az útmutató az építéshez
	 * @return a robot nem tud építeni, így nemmel tér vissza
	 */
	public boolean AnyagokTorol(Utmutato bazis) {
		return false;
	}
}
