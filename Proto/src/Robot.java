
import java.util.Random;


public class Robot extends Entitas implements Intelligencia {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7370920782066937702L;
	/**
	 * azt sz�molja, hogy h�nyszor p�ld�nyos�tott�k az oszt�lyt
	 */
	private static int count=1;
	
	/**
	 * A Robot oszt�ly konstruktora, megh�vja az �soszt�ly konstruktor�t
	 * �s be�ll�tja a robot egyedi azonos�t�j�t 
	 */
	Robot(){
		super();
		ID="r0"+count++;
	}
	
	/**
	 *Lek�r az aktu�lis aszteroid�t�l egy v�letlenszer� szomsz�dot,
	 *amire �tmozog
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
	 *Felrobban�s sor�n �tker�l egy v�letlenszer� aszteroid�ra
	 *a RandomMozgas() f�ggv�ny seg�ts�g�vel
	 */
	public void Felrobban() {
		RandomMozgas();
	}
	
	/**
	 *Elt�vol�tja mag�t az aktu�lis aszteroid�r�l, �s kit�rli mag�t
	 *az Aszteroida�v robotok list�j�b�l
	 */
	public void Halal() {
		aszteroida.Ledob(this);
		Main.game.GetOv().MinuszRobot(this);
	}
	
	/**
	 *V�letlenszer�en v�laszt, hogy az adott k�rben mozogni vagy aszteroid�t f�rni fog a Robot
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
	 *Ha az aszteroid�nak, amin �ll a Robot nem 0 a k�penyvastags�ga,
	 *akkor megf�rja a k�peny�t. 
	 */
	public void Furas() {
		if(aszteroida.getKopenyVastagsag() > 0)
			aszteroida.KeregCsokken();
	}
	
	/**
	 *V�ltoztat�st nem v�grehajt�, logikai hamissal visszat�r� f�ggv�ny,
	 *mert a Robot nem tud r�szt venni a B�zis�p�t�sben
	 */
	public boolean BazisEpit(Utmutato u) { return false; }

	/**
	 *�res t�rzs� f�ggv�ny, mert a Robot nem tud nyersanyagot b�ny�szni
	 */
	public void Banyaszat() {}

	/**
	 *Ki�rja a konzolra a Robot k�l�nb�z� tulajdons�gait
	 */
	public void kiir() {
		System.out.println(this + ": " + ID);
		System.out.println("Aszteroida: " + aszteroida.getID());
	}
	
	/**
	 * Ki�rja az entit�s f� tulajdons�g�t
	 * @return az entit�s t�pusa
	 */
	public String toString() {
		return "Robot";
	}
	
	/**
	 * Az �p�t�s sor�n  visszaadja, hogy teljes�tve van e az �tmutat�.
	 * @param bazis : Az �tmutat� az �p�t�shez
	 * @return a robot nem tud �p�teni, �gy nemmel t�r vissza
	 */
	public boolean AnyagokTorol(Utmutato bazis) {
		return false;
	}
}
