import java.util.Random;

public class Ufo extends Entitas implements Intelligencia {
	
	
	private static int count=0;
	private String ID;
	
	Ufo(){
		super();
		ID="uf0"+count++;
	}
	
	/**
	 *véletlenszerû mozgást valósít meg az aszteroida egy szomszédjára
	 */
	public void RandomMozgas() {
		Szomszed b = aszteroida.SzomszedotAd();
		aszteroida.Ledob(this);
		b.Befogad(this);	
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
	
	
	public void Furas() {
	}

	public boolean BazisEpit(Utmutato u) { return false; }

	public void kiir() {
		System.out.println(this + ": " + ID);
		System.out.println("Aszteroida: " + aszteroida.getID());
	}
	
}
