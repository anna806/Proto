package src;


public abstract class Entitas {
	
	/**
	 * az aszteroida, amin tart�zkodik az adott Entit�s
	 */
	protected Aszteroida aszteroida;
	/**
	 * az Entit�s egyedi azonos�t�ja
	 */
	protected String ID;
	
	/**
	 * absztrakt f�ggv�ny az Entit�sok f�r�s m�velet�re,
	 * a lesz�rmazottak val�s�tj�k meg
	 */
	public abstract void Furas();
	
	/**
	 * absztrakt f�ggv�ny az Entit�sok hal�l�ra,
	 * a lesz�rmazottak val�s�tj�k meg
	 */
	public abstract void Halal();
	
	/**
	 * absztrakt f�ggv�ny az Entit�sok felobban�s�ra,
	 * a lesz�rmazottak val�s�tj�k meg
	 */
	public abstract void Felrobban();
	
	/**
	 * �ny az Entit�sok l�ptet�s�re,
	 * a absztrakt f�ggvz�rmazottak val�s�tj�k meg
	 */
	public abstract void Lep();
	
	/**
	 * absztrakt f�ggv�ny az Entit�sok b�ny�szat m�velet�re,
	 * a lesz�rmazottak val�s�tj�k meg
	 */
	public abstract void Banyaszat();
	
	/**
	 * Be�ll�tja az Entit�s aszteroida tagv�ltoz�j�t a param�terben kapott aszteroid�ra
	 * @param a az aszteroida, amire be kell �ll�tani a tagv�ltoz�t
	 */
	public void SetAszteroida(Aszteroida a) {
		aszteroida = a;
	}
	
	/**
	 * absztrakt f�ggv�ny az Entit�sok b�zis�p�t�s m�velet�re,
	 * a lesz�rmazottak val�s�tj�k meg
	 * @param u az �tmutat�, ami sz�ks�ges a b�zis�p�t�shez
	 * @return logikai �rt�k, ami megmondja, hogy sikeres volt-e a b�zis�p�t�s vagy sem
	 */
	public abstract boolean BazisEpit(Utmutato u);
	
	/**
	 * Be�ll�tja az Entit�s egyedi azonos�t�j�t a param�terben kapott id-re
	 * @param id a string, ami az Entit�s egyedi azonos�t�ja lesz
	 */
	public void SetID(String id) {
		ID = id;
	}
	
	/**
	 * Visszaadja az Entit�s egyedi azonos�t�j�t
	 * @return az Entit�s egyedi azonos�t�ja
	 */
	public String getID() {
		return ID;
	}
	
	/**
	 * Visszaadja, hogy az Entit�s melyik aszteroid�n tart�zkodik
	 * @return az Entit�s aszteroida tagv�ltoz�ja
	 */
	public Aszteroida getAszteroida() {
		return aszteroida;
	}
	
	public abstract boolean AnyagokTorol(Utmutato bazis);   //!!!!!!!!!!!!!!!!!!!!
}
