
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Teleportkapu extends Szomszed implements Intelligencia{
	/**
	 * A szerializálást elõsegítõ ID
	 */
	private static final long serialVersionUID = 4398516018408113362L;
	/**
	 * számolja, hányszor példányosították az osztályt
	 */
	private static int count=1;						
	/**
	 * az aszteroida, amelyen a telepes tartózkodik
	 */
	private Aszteroida aszteroida;					
	/**
	 * megadja, hogy a teleportkapu meg van-e kergülve
	 */
	private boolean megkergult;						
	/**
	 * a teleportkapu párja
	 */
	private Teleportkapu parja;	
	
	/**
	 * default constructor, beállítja az akapértelmezett értékeketés az egyedi azonosítót
	 */
	public Teleportkapu() {
		aszteroida=null;
		megkergult=false;
		parja=null;
		ID="k0"+count++;
	}
	
	/**
	 * visszaadja a teleportkapu párját
	 * @return a kapu párja
	 */
	public Teleportkapu getParja() {
		return parja;
	}
	
	/**
	 * a paraméterként kapott teleportkaput beállítja a kapu párjaként
	 * @param par a kapott teleportkapu
	 */
	public void setParja(Teleportkapu par) {
		parja=par;
	}
	
	/**
	 * @return aszteroida: a teleportkapu tartózkodási helye
	 */
	public Aszteroida getAszter() {
		return aszteroida;
	}
	
	/**
	 * a kapott aszteroidát beállítja a kapu tartózkodási helyének
	 * @param a: a kapott aszteroida
	 */
	public void setAszter(Aszteroida a) { aszteroida=a;
	}
	
	/**
	 * @return visszatérési értékében visszaadja, hogy a teleportkapu párja mely aszteroidán helyezkedik el
	 * vagyis, hogy melyik aszteroida érhetõ el teleportálással
	 */
	public Aszteroida ParHelye() {
		if(parja == null)
			return null;
		else
			return getParja().getAszter();
	}
	
	/**
	 *A teleportkapu felrobban, ennek során megsemmisül, ezért a párjában átállítja 
	 *a párja változót => a párjával teleportálva az utazó ugyanazon az aszteroidán marad
	 */
	public void Robban() {
		aszteroida.SzomszedTorol(this);
		parja.setParja(parja);
	}
	
	/**
	 * a paraméterként kapott entitást befogadja, vagyis átvezeti arra az aszteroidára, amely rajta 
	 * keresztül elérhetõ
	 *@param a: kapott entitás
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
	 *a Szomszéd Napvihar függvényét valósítja meg, a teleportkapu MegKergul függvényét valósítja meg
	 */
	public void Napvihar() {
		MegKergul();
	}
	/**
	 *a teleportkapu megkergülését okozza, a megkergult változót true-ra állítja
	 */
	public void MegKergul() {
		megkergult=true;
	}
	
	/**
	 *a teleportkapu mozgását megvalósító függvény
	 *amennyiben a kapu meg van kergülve, körönként hívódik.
	 *véletlenszerûen választja ki az aszteroida egy szomszédját, és arra átvándorol
	 */
	public void RandomMozgas() {   
		if(megkergult==true) {
			Szomszed cel= aszteroida.SzomszedotAd();
			aszteroida.SzomszedTorol(this);
			cel.KapuBefogad(this);
		}         
	}
	
	/**
	 * a paraméterként kapott teleportkaput átirányítja arra azz aszteroidár, 
	 * amely rajta keresztül elérhetõ.
	 *@param k: kapott kapu
	 */
	public void KapuBefogad(Teleportkapu k) {
		ParHelye().KapuBefogad(k);
	}
	
	/**
	 *Üres törzsû függvény, mert a Teleportkapu nem tud Szomszédot törölni
	 */
	public void SzomszedTorol(Szomszed a) {}
	
	/**
	 * Visszaadja, hogy az adott Teleportkapu kergült-e vagy sem
	 * @return a Teleportkapu megkergult tagváltozója
	 */
	public String getMegkergult() {
		if(megkergult) return "true";
		else return "false";
	
	}

	/**
	 * Kiirja az adott Teleportkapu külünbüzõ tulajdonágait a konzolra
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
