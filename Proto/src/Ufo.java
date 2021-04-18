import java.util.Random;

public class Ufo extends Entitas implements Intelligencia {
	
	
	private static int count=0;
	private String ID;
	
	Ufo(){
		super();
		ID="uf0"+count++;
	}
	
	/**
	 *v�letlenszer� mozg�st val�s�t meg az aszteroida egy szomsz�dj�ra
	 */
	public void RandomMozgas() {
		Szomszed b = aszteroida.SzomszedotAd();
		aszteroida.Ledob(this);
		b.Befogad(this);	
	}
	
	/**
	 *Az ufo hal�l�t megval�s�t� fv, az aszteroida�v ufok list�j�b�l kit�rli az egyedet
	 */
	public void Halal() {	
		Main.game.GetOv().MinuszUfo(this);
	}
	
	/**
	 *az ufo felrobban�s�t megval�s�t� fv, megh�vja a Halal() f�ggv�nyt
	 */
	public void Felrobban() {
		Halal();
	}
	
	/**
	 *az ufo l�p�se: ugyanolyan val�sz�n�s�ggel b�ny�szik, mint hogy tov�bb l�p egy random aszteroid�ra
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
	 *b�ny�szat: az aktu�lis aszteroid�n megpr�b�l anyagot kinyerni
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
