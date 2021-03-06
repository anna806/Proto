package proto;

public class Szen extends Nyersanyag {
	/**
	 * A szerializ?l?st el?seg?t? ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * az id egyedis?g?t biztos?t? sz?ml?l?
	 */
	private static int count=1;
	
	/**
	 * A Sz?n oszt?ly konstruktora.
	 */
	public Szen(){
		super();
		ID="sz0"+count++;
	}
	
	/**
	 * A Nyersanyag bet?lt?s?t v?gzi a telepes inventoryj?ba.
	 * @param A Telepes, aki b?ny?szik.
	 */
	public void Betolt(Telepes a) {
		a.AddNyersanyag(this);
	}
	
	/**
	 * A nyersanyag napk?zelbe ker?l?s?t v?gz? f?ggv?ny.
	 * @param Az aszteroida, amiben a nyersanyag van.
	 */
	public void Napkozel(Aszteroida a) {
	}
	
	/**
	 * A Nyersanyagok ?sszehasonl?t?s?t v?gz? f?ggv?ny.
	 * @param A Nyersanyag, amivel ?ssze kell hasonl?tania ?nmag?t.
	 * @return Egy logikai v?ltoz?, ami megmondja, hogy ugyanolyan faj?tj?ak-e az ?sszehasonl?tott Nyersanyagok.
	 */
	public boolean Kompatibilis(Nyersanyag ny) {
		return ny instanceof Szen;
	}
	/**
	 * Amennyiben van expozicioja a nyersanyagnak, akkor azt megn?veli.
	 * @param A sz?m, amivel n?velje az expoziciot.
	 */
	public void setExp(int i) {
		
	}
	
	/**
	 * Visszaadja egy stringben a nyersanyag t?pus?t
	 * @return a nyersaynag t?pusa
	 */
	public String toString() {
		return "Szen";
	}

	/**
	 * Visszaadja a nyersanyag expoz?ci?s sz?m?t
	 * @return a sz?nnek nincs expoz?ci?s sz?ma, ?gy 0-t ad vissza
	 */
	@Override
	protected int getExp() {
		return 0;
	}
}
