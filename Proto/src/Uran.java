//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Ur�n.java
//  @ Date : 2021. 04. 08.
//  @ Author : 
//
//




public class Uran extends Nyersanyag {
	private int expozicio;
	
	public Uran() {
		expozicio=0;
	}
	
	public void Betolt(Telepes a) {
		a.AddNyersanyag(this);
	}
	
	public void Napkozel(Aszteroida a) {
		if(expozicio>=3) {
			a.Robban();
		}
		else
			expozicio++;
	}
	
	public boolean Kompatibilis(Nyersanyag ny) {
		return ny instanceof Uran;
	}
}
