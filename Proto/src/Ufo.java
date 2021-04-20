
import java.util.Random;

public class Ufo extends Entitas implements Intelligencia {
	
	/**
	 * Az id egyedis�g�t biztos�t� sz�ml�l�
	 */
	private static int count=0;
	/**
	 * Az azonos�t�shoz sz�ks�ges ID
	 */
	//private String ID;
	
	/**
	 * Uf� konstruktora
	 */
	Ufo(){
		super();
		ID="u0"+count++;
	}
	
	/**
	 *v�letlenszer� mozg�st val�s�t meg az aszteroida egy szomsz�dj�ra
	 */
	public void RandomMozgas() {
		aszteroida.Ledob(this);
		if(aszteroida.SzomszedotAd() != null)
			aszteroida.SzomszedotAd().Befogad(this);
		else
			Halal();
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
	
	/**
	 * A f�r�shoz sz�ks�ges f�ggv�ny - az uf� nem tud f�rni
	 */
	public void Furas() {
	}
	
	/**
	 * A b�zis�p�t�shez sz�ks�ges f�ggv�ny - az uf� nem tud b�zist �p�teni
	 */
	public boolean BazisEpit(Utmutato u) { return false; }

	/**
	 * Ki�rja az uf� tulajdons�gait a kimenetre
	 */
	public void kiir() {
		System.out.println(this + ": " + ID);
		System.out.println("Aszteroida: " + aszteroida.getID());
	}
	
	/**
	 * Visszaadja egy Stringben az entit�s t�pus�t
	 * @return az entit�s t�pusa stringben
	 */
	public String toString() {
		return "Ufo";
	}

	/**
	 * Az �p�t�s sor�n  visszaadja, hogy teljes�tve van e az �tmutat�.
	 * @param bazis : Az �tmutat� az �p�t�shez
	 * @return a robot nem tud �p�teni, �gy nemmel t�r vissza
	 */
	public boolean AnyagokTorol(Utmutato bazis) { return false; }
	
}
