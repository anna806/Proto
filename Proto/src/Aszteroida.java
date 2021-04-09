import java.util.ArrayList;
import java.util.List;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Aszteroida.java
//  @ Date : 2021. 04. 08.
//  @ Author : 
//
//




public class Aszteroida extends Szomszed {
	private int kopenyVastagsag;
	private boolean napkozel;
	private List<Entitas> entitasok = new ArrayList<>();
	private List<Szomszed> szomszedok = new ArrayList<>();
	private Nyersanyag belsoAnyag;
	
	List<Szomszed> getSzomszedok(){
		return szomszedok;
	}
	
	public Nyersanyag AnyagKinyeres() {
	}
	
	//ez �gy helyes?
	public void Ledob(Entitas a) {
		entitasok.remove(a);
	}
	
	public void Befogad(Entitas a) {
		entitasok.add(a);
	}
	
	public void Napvihar() {
		if(belsoAnyag != null) {
			for(Entitas e: entitasok) {
				e.Halal();
			}
		}
		//kapukon?
		//nekik m�r nem k�ne t�bb szomsz�dot h�vni
	}
	
	public void SzomszedTorol(Szomszed a) {
		szomszedok.remove(a);
	}
	
	public void BazisEpit() {
	}
	
	public void KeregCsokken() {
		if(kopenyVastagsag != 0) {
			kopenyVastagsag--;
		}
	}
	
	public void Robban() {
		for(Entitas e: entitasok) {
			e.Felrobban();
		}
	}
	
	public Szomszed SzomszedotAd() {
		return null;
	}
	
	public void Raktaroz(Nyersanyag a, Telepes t) {
		if(belsoAnyag == null && kopenyVastagsag == 0) {
			t.RemoveNyersanyag(a);
		}
		if(napkozel) {
			belsoAnyag.Napkozel(this);
		}
	}
	
	public void AnyagTorol() {
		belsoAnyag = null;
	}
	
	public void StartNapvihar() {
		this.Napvihar();
		for(Szomszed sz: szomszedok) {
			sz.Napvihar();
		}
	}	
	
	public void KapuBefogad(Teleportkapu k) {
	}
}
