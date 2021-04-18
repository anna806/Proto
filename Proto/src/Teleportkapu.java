import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;






public class Teleportkapu extends Szomszed implements Intelligencia{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4398516018408113362L;
	private static int count=0;
	private Aszteroida aszteroida;
	private boolean megkergult;
	private Teleportkapu parja;
	private String ID;
	
	public Teleportkapu() {
		aszteroida=null;
		megkergult=false;
		parja=null;
		ID="tk0"+count++;
	}
	
	public Teleportkapu getParja() {
		return parja;
	}
	
	public void setParja(Teleportkapu par) {
		parja=par;
	}
	
	public Aszteroida getAszter() {
		return aszteroida;
	}
	
	public void setAszter(Aszteroida a) {
		aszteroida=a;
	}
	
	public Aszteroida ParHelye() {
		return this.getParja().getAszter();
	}
	
	public void Robban() {
		aszteroida.SzomszedTorol(this);
		parja.setParja(parja);
	}
	
	public void Befogad(Entitas a) {
		a.SetAszteroida(this.ParHelye());
		Aszteroida x= new Aszteroida();
		x= this.ParHelye();
		x.Befogad(a);
	}
	
	public void Napvihar() {
		MegKergul();
	}
	public void MegKergul() {
		megkergult=true;
	}
	
	public void RandomMozgas() {   
		if(megkergult==true) {
			Szomszed cel= aszteroida.SzomszedotAd();
			aszteroida.SzomszedTorol(this);
			cel.KapuBefogad(this);
		}
		          
	}
	
	public void KapuBefogad(Teleportkapu k) {
		
		Aszteroida a= this.ParHelye();
		a.KapuBefogad(k);
	}
	
	public String getID() {
		return ID;
	}
	
	public void SzomszedTorol(Szomszed a) {}
	
	public String getMegkergult() {
		if(megkergult) return "true";
		else return "false";
	
	}
}
