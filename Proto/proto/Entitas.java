package proto;
import java.io.Serializable;

public abstract class Entitas implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6752190916141032408L;
	/**
	 * az aszteroida, amin tartózkodik az adott Entitás
	 */
	protected Aszteroida aszteroida;
	/**
	 * az Entitás egyedi azonosítója
	 */
	protected String ID;
	
	/**
	 * absztrakt függvény az Entitások fúrás mûveletére,
	 * a leszármazottak valósítják meg
	 */
	public abstract void Furas();
	
	/**
	 * absztrakt függvény az Entitások halálára,
	 * a leszármazottak valósítják meg
	 */
	public abstract void Halal();
	
	/**
	 * absztrakt függvény az Entitások felobbanására,
	 * a leszármazottak valósítják meg
	 */
	public abstract void Felrobban();
	
	/**
	 * absztrakt függvény az Entitások léptetésére,
	 * a absztrakt függvzármazottak valósítják meg
	 */
	public abstract void Lep();
	
	/**
	 * absztrakt függvény az Entitások bányászat mûveletére,
	 * a leszármazottak valósítják meg
	 */
	public abstract void Banyaszat();
	
	/**
	 * Beállítja az Entitás aszteroida tagváltozóját a paraméterben kapott aszteroidára
	 * @param a az aszteroida, amire be kell állítani a tagváltozót
	 */
	public void SetAszteroida(Aszteroida a) {
		aszteroida = a;
	}
	
	/**
	 * absztrakt függvény az Entitások bázisépítés mûveletére,
	 * a leszármazottak valósítják meg
	 * @param u az Útmutató, ami szükséges a bázisépítéshez
	 * @return logikai érték, ami megmondja, hogy sikeres volt-e a bázisépítés vagy sem
	 */
	public abstract boolean BazisEpit(Utmutato u);
	
	/**
	 * Beállítja az Entitás egyedi azonosítóját a paraméterben kapott id-re
	 * @param id a string, ami az Entitás egyedi azonosítója lesz
	 */
	public void SetID(String id) {
		ID = id;
	}
	
	/**
	 * Visszaadja az Entitás egyedi azonosítóját
	 * @return az Entitás egyedi azonosítója
	 */
	public String getID() {
		return ID;
	}
	
	/**
	 * Visszaadja, hogy az Entitás melyik aszteroidán tartózkodik
	 * @return az Entitás aszteroida tagváltozója
	 */
	public Aszteroida getAszteroida() {
		return aszteroida;
	}
	
	/**
	 * Az építés során kitörli az anyagokat az enititás inventoryjából és visszaadja,
	 * hogy teljesítve van e az útmutató. A leszármazottak valósítják meg.
	 * @param bazis : Az útmutató az építéshez
	 * @return kitörölte-e az összes anyagot ami az útmutatóban van 
	 */
	public abstract boolean AnyagokTorol(Utmutato bazis);
	public abstract boolean Kompatibilis(Entitas e);
	
	public abstract String getNev();
}
