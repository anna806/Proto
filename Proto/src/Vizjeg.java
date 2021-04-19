//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : V�zj�g.java
//  @ Date : 2021. 04. 08.
//  @ Author : 
//
//




public class Vizjeg extends Nyersanyag {
	/**
	 * A szerializ�l�st el�seg�t� ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * az id egyedis�g�t biztos�t� sz�ml�l�
	 */
	private static int count=0;
	/**
	 * A nyersanyag azonos�t�s�ra szolg�l� ID
	 */
	//private String ID;
	
	/**
	 * A Vizjeg oszt�ly konstruktora.
	 */
	Vizjeg(){
		super();
		ID="vj0"+count++;
	}
	
	/**
	 * A Nyersanyag bet�lt�s�t v�gzi a telepes inventoryj�ba.
	 * @param A Telepes, aki b�ny�szik.
	 */
	public void Betolt(Telepes a) {
		a.AddNyersanyag(this);
	}
	
	/**
	 * A nyersanyag napk�zelbe ker�l�s�t v�gz� f�ggv�ny.
	 * @param Az aszteroida, amiben a nyersanyag van.
	 */
	public void Napkozel(Aszteroida a) {
		a.AnyagTorol();
	}
	
	/**
	 * A Nyersanyagok �sszehasonl�t�s�t v�gz� f�ggv�ny.
	 * @param A Nyersanyag, amivel �ssze kell hasonl�tania �nmag�t.
	 * @return Egy logikai v�ltoz�, ami megmondja, hogy ugyanolyan faj�tj�ak-e az �sszehasonl�tott Nyersanyagok.
	 */
	public boolean Kompatibilis(Nyersanyag ny) {
		return ny instanceof Vizjeg;
	}
	/**
	 * Amennyiben van expozicioja a nyersanyagnak, akkor azt megn�veli.
	 * @param A sz�m, amivel n�velje az expoziciot.
	 */
	public void setExp(int i) {
		
	}
}
