

public class Szen extends Nyersanyag {
	/**
	 * A szerializálást elõsegítõ ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * az id egyediségét biztosító számláló
	 */
	private static int count=1;
	/**
	 * A nyersanyag azonosítására szolgáló ID
	 */
	//private String ID;
	
	/**
	 * A Szén osztály konstruktora.
	 */
	Szen(){
		super();
		ID="sz0"+count++;
	}
	
	/**
	 * A Nyersanyag betöltését végzi a telepes inventoryjába.
	 * @param A Telepes, aki bányászik.
	 */
	public void Betolt(Telepes a) {
		a.AddNyersanyag(this);
	}
	
	/**
	 * A nyersanyag napközelbe kerülését végzõ függvény.
	 * @param Az aszteroida, amiben a nyersanyag van.
	 */
	public void Napkozel(Aszteroida a) {
	}
	
	/**
	 * A Nyersanyagok összehasonlítását végzõ függvény.
	 * @param A Nyersanyag, amivel össze kell hasonlítania önmagát.
	 * @return Egy logikai változó, ami megmondja, hogy ugyanolyan fajátjúak-e az összehasonlított Nyersanyagok.
	 */
	public boolean Kompatibilis(Nyersanyag ny) {
		return ny instanceof Szen;
	}
	/**
	 * Amennyiben van expozicioja a nyersanyagnak, akkor azt megnöveli.
	 * @param A szám, amivel növelje az expoziciot.
	 */
	public void setExp(int i) {
		
	}
	
	/**
	 * Visszaadja egy stringben a nyersanyag típusát
	 * @return a nyersaynag típusa
	 */
	public String toString() {
		return "Szen";
	}

	/**
	 * Visszaadja a nyersanyag expozíciós számát
	 * @return a szénnek nincs expozíciós száma, így 0-t ad vissza
	 */
	@Override
	protected int getExp() {
		return 0;
	}
}
