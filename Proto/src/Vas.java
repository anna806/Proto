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
	
	
	public void Betolt(Telepes a) {
		a.AddNyersanyag(this);
	}
	
	public void Napkozel(Aszteroida a) {
	}
	
	public boolean Kompatibilis(Nyersanyag ny) {
		return ny instanceof Vas;
	}
}
