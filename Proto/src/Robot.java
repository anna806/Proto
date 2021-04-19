import java.util.Random;


public class Robot extends Entitas implements Intelligencia {
	
	/**
	 * azt sz�molja, hogy h�nyszor p�ld�nyos�tott�k az oszt�lyt
	 */
	private static int count=0;				
	//private String ID;						//a Robot egyedi azonos�t�ja
	
	
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
		//Szomszed b = aszteroida.SzomszedotAd();
		aszteroida.Ledob(this);
		aszteroida.SzomszedotAd().Befogad(this);
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
}
