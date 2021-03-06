package proto;
//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Vas.java
//  @ Date : 2021. 04. 08.
//  @ Author : 
//
//




public class Vas extends Nyersanyag {
	
	/**
	 * A szerializ?l?st el?seg?t? ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * az id egyedis?g?t biztos?t? sz?ml?l?
	 */
	private static int count=1;
	
	/**
	 * A Vas oszt?ly konstruktora.
	 */
	public Vas() {
		super();
		ID="v0"+count++;
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
		return ny instanceof Vas;
	}
	
	/**
	 * Amennyiben van expozicioja a nyersanyagnak, akkor azt megn?veli.
	 * @param A sz?m, amivel n?velje az expoziciot.
	 */
	public void setExp(int i) {
		
	}
	
	/**
	 * Visszaadja a nyersanyag t?pus?t string k?nt
	 * @return a nyersanyag t?pusa
	 */
	public String toString() {
		return "Vas";
	}

	/**
	 * Visszaadja a nyersanyag expoz?ci?s sz?m?t
	 * @return a vasnak nincs expoz?ci?s sz?ma, ?gy 0-t ad vissza
	 */
	@Override
	protected int getExp() {
		return 0;
	}
}
