import java.util.Random;

public class Ufo extends Entitas implements Intelligencia {
	
	
	private static int count=0;
	private String ID;
	
	Ufo(){
		super();
		ID="uf0"+count++;
	}
	
	public void RandomMozgas() {
		Szomszed b = aszteroida.SzomszedotAd();
		aszteroida.Ledob(this);
		b.Befogad(this);	
	}
	
	public void Halal() {	
		;
	}
	
	public void Felrobban() {
		Halal();
	}
	
	public void Lep() {
		Random rand = new Random();
		int r = rand.nextInt();
		if(r % 2 == 0)
			Banyaszat();
		else
			RandomMozgas();
	}
	
	public void Banyaszat() {
		Nyersanyag belsoAnyag = aszteroida.AnyagKinyeres();
		aszteroida.AnyagTorol();
	}
	
	
	public void Furas() {
	}

	public boolean BazisEpit(Utmutato u) { return false; }
	
}
