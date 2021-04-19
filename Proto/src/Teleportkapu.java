import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;






/**
 * @author Luca
 *
 */
public class Teleportkapu extends Szomszed implements Intelligencia{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4398516018408113362L;
	private static int count=0;						//sz�molja, h�nyszor p�ld�nyos�tott�k az oszt�lyt
	private Aszteroida aszteroida;					//az aszteroida, amelyen a telepes tart�zkodik
	private boolean megkergult;						//megadja, hogy a teleportkapu meg van-e kerg�lve
	private Teleportkapu parja;						//a teleportkapu p�rja
	private String ID;								//a teleportkapu egyedi azonos�t�ja
	
	/**
	 * default constructor, be�ll�tja az akap�rtelmezett �rt�keket�s az egyedi azonos�t�t
	 */
	public Teleportkapu() {
		aszteroida=null;
		megkergult=false;
		parja=null;
		ID="tk0"+count++;
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
		return this.getParja().getAszter();
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
		a.SetAszteroida(this.ParHelye());
		Aszteroida x= new Aszteroida();
		x= this.ParHelye();
		x.Befogad(a);
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
		
		Aszteroida a= this.ParHelye();
		a.KapuBefogad(k);
	}
	
//	public String getID() {
//		return ID;
//	}
	
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
		System.out.println(this + ": " + ID);
		System.out.println("Aszteroida: " + aszteroida.getID());
		System.out.println("Parja: " + parja.ID);
		System.out.println("Kergult - e: " + megkergult);
	}
}
