
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Teleportkapu extends Szomszed implements Intelligencia{
	/**
	 * A szerializ�l�st el�seg�t� ID
	 */
	private static final long serialVersionUID = 4398516018408113362L;
	/**
	 * sz�molja, h�nyszor p�ld�nyos�tott�k az oszt�lyt
	 */
	private static int count=1;						
	/**
	 * az aszteroida, amelyen a telepes tart�zkodik
	 */
	private Aszteroida aszteroida;					
	/**
	 * megadja, hogy a teleportkapu meg van-e kerg�lve
	 */
	private boolean megkergult;						
	/**
	 * a teleportkapu p�rja
	 */
	private Teleportkapu parja;	
	
	/**
	 * default constructor, be�ll�tja az akap�rtelmezett �rt�keket�s az egyedi azonos�t�t
	 */
	public Teleportkapu() {
		aszteroida=null;
		megkergult=false;
		parja=null;
		ID="k0"+count++;
	}
	
	/**
	 * visszaadja a teleportkapu p�rj�t
	 * @return a kapu p�rja
	 */
	public Teleportkapu getParja() {
		return parja;
	}
	
	/**
	 * a param�terk�nt kapott teleportkaput be�ll�tja a kapu p�rjak�nt
	 * @param par a kapott teleportkapu
	 */
	public void setParja(Teleportkapu par) {
		parja=par;
	}
	
	/**
	 * @return aszteroida: a teleportkapu tart�zkod�si helye
	 */
	public Aszteroida getAszter() {
		return aszteroida;
	}
	
	/**
	 * a kapott aszteroid�t be�ll�tja a kapu tart�zkod�si hely�nek
	 * @param a: a kapott aszteroida
	 */
	public void setAszter(Aszteroida a) { aszteroida=a;
	}
	
	/**
	 * @return visszat�r�si �rt�k�ben visszaadja, hogy a teleportkapu p�rja mely aszteroid�n helyezkedik el
	 * vagyis, hogy melyik aszteroida �rhet� el teleport�l�ssal
	 */
	public Aszteroida ParHelye() {
		if(parja == null)
			return null;
		else
			return getParja().getAszter();
	}
	
	/**
	 *A teleportkapu felrobban, ennek sor�n megsemmis�l, ez�rt a p�rj�ban �t�ll�tja 
	 *a p�rja v�ltoz�t => a p�rj�val teleport�lva az utaz� ugyanazon az aszteroid�n marad
	 */
	public void Robban() {
		aszteroida.SzomszedTorol(this);
		parja.setParja(parja);
	}
	
	/**
	 * a param�terk�nt kapott entit�st befogadja, vagyis �tvezeti arra az aszteroid�ra, amely rajta 
	 * kereszt�l el�rhet�
	 *@param a: kapott entit�s
	 */
	public void Befogad(Entitas a) {
		if(ParHelye() != null) {
			a.SetAszteroida(ParHelye());
			ParHelye().Befogad(a);
		}
		aszteroida.Befogad(a);
		System.out.println(a.getAszteroida().getID());
	}
	
	/**
	 *a Szomsz�d Napvihar f�ggv�ny�t val�s�tja meg, a teleportkapu MegKergul f�ggv�ny�t val�s�tja meg
	 */
	public void Napvihar() {
		MegKergul();
	}
	/**
	 *a teleportkapu megkerg�l�s�t okozza, a megkergult v�ltoz�t true-ra �ll�tja
	 */
	public void MegKergul() {
		megkergult=true;
	}
	
	/**
	 *a teleportkapu mozg�s�t megval�s�t� f�ggv�ny
	 *amennyiben a kapu meg van kerg�lve, k�r�nk�nt h�v�dik.
	 *v�letlenszer�en v�lasztja ki az aszteroida egy szomsz�dj�t, �s arra �tv�ndorol
	 */
	public void RandomMozgas() {   
		if(megkergult==true) {
			Szomszed cel= aszteroida.SzomszedotAd();
			aszteroida.SzomszedTorol(this);
			cel.KapuBefogad(this);
		}         
	}
	
	/**
	 * a param�terk�nt kapott teleportkaput �tir�ny�tja arra azz aszteroid�r, 
	 * amely rajta kereszt�l el�rhet�.
	 *@param k: kapott kapu
	 */
	public void KapuBefogad(Teleportkapu k) {
		ParHelye().KapuBefogad(k);
	}
	
	/**
	 *�res t�rzs� f�ggv�ny, mert a Teleportkapu nem tud Szomsz�dot t�r�lni
	 */
	public void SzomszedTorol(Szomszed a) {}
	
	/**
	 * Visszaadja, hogy az adott Teleportkapu kerg�lt-e vagy sem
	 * @return a Teleportkapu megkergult tagv�ltoz�ja
	 */
	public String getMegkergult() {
		if(megkergult) return "true";
		else return "false";
	
	}

	/**
	 * Kiirja az adott Teleportkapu k�l�nb�z� tulajdon�gait a konzolra
	 */
	public void kiir() {
//		System.out.println(this + ": " + ID);
//		System.out.println("Aszteroida: " + aszteroida.getID());
//		System.out.println("Parja: " + parja.ID);
//		System.out.println("Kergult - e: " + megkergult);
		System.out.println("Teleportkapu: " + this);
	}
	
	public String toString() {
		String s = "\n\"ID\": \"" + ID + "\"\n\"aszteroida\": \"" + aszteroida.getID() +
				"\n\"megkergult\": \"" + megkergult + "\"\n\"parja\": \"" + parja.getID() + "\n";
		return s;
	}
}
