//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Sz�n.java
//  @ Date : 2021. 04. 08.
//  @ Author : 
//
//




public class Szen extends Nyersanyag {
	
	
	public void Betolt(Telepes a) {
		a.AddNyersanyag(this);
	}
	
	public void Napkozel(Aszteroida a) {
	}
	
	public boolean Kompatibilis(Nyersanyag ny) {
		return ny instanceof Szen;
	}
}
